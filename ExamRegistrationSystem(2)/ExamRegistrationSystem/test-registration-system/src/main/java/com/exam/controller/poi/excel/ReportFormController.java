package com.exam.controller.poi.excel;

import com.exam.dao.ExamDao;
import com.exam.dao.ExamUserDao;
import com.exam.pojo.model.ExamModel;
import com.exam.pojo.model.ExamUserModel;
import com.exam.pojo.param.ExamUserParam;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;

@RestController
@RequestMapping(value = "/report")
public class ReportFormController {

    @Autowired
    private ExamUserDao examUserDao;

    @Autowired
    private ExamDao examDao;

    /**
     * 导出报表
     *
     * @return
     */
    @RequestMapping(value = "/export/{examId}")
    @ResponseBody
    public void export(@PathVariable("examId") String examId, HttpServletRequest request, HttpServletResponse response) throws Exception {
        List<ExamUserModel> examUserModels = examUserDao.selectByExampleId(examId);
        ExamModel examModel = examDao.queryById(examId);
        int size = examUserModels.size();
        String[][] content = new String[size][9];
        //excel标题
        String[] title = {"序号", "姓名", "身份证号", "状态", "考场", "准考证号", "用户名", "考试ID", "分数"};
        //excel文件名
        String fileName = examModel.getTitle() + "报名信息表" + ".xls";
        //sheet名
        String sheetName = examModel.getTitle() + "报名信息表";
//        String[][] content = {{"高歌", "89"}, {"高歌5", "89"}, {"高歌55", "89"}, {"高歌6", "89"}};

        for (int i = 0; i < examUserModels.size(); i++) {
            content[i][0] = i + "";
            content[i][1] = examUserModels.get(i).getApplyName();
            content[i][2] = examUserModels.get(i).getIdNumber();
            content[i][3] = examUserModels.get(i).getStatus().getMsg();
            content[i][4] = examUserModels.get(i).getExamRoom();
            content[i][5] = examUserModels.get(i).getExamNumber();
            content[i][6] = examUserModels.get(i).getUserId();
            content[i][7] = examUserModels.get(i).getExamId();
            content[i][8] = examUserModels.get(i).getScore();

        }

        //创建HSSFWorkbook
        HSSFWorkbook wb = ExcelUtil.getHSSFWorkbook(sheetName, title, content, null);

        //响应到客户端
        try {
            this.setResponseHeader(response, fileName);
            OutputStream os = response.getOutputStream();
            wb.write(os);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // 响应给浏览器
    public void setResponseHeader(HttpServletResponse response, String fileName) {
        try {
            try {
                fileName = new String(fileName.getBytes(), "ISO8859-1");
            } catch (UnsupportedEncodingException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
            response.setContentType("application/octet-stream;charset=ISO8859-1");
//            response.setHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            response.addHeader("Pargam", "no-cache");
            response.addHeader("Cache-Control", "no-cache");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 读取Excel表格
     *
     * @throws Exception
     */
    @PostMapping("/read")
    public String readExcel(@RequestParam("file") MultipartFile file) throws Exception {
        //用流的方式先读取到你想要的excel的文件
        String localFileDirPath = System.getProperty("user.dir") + File.separator + "excel";
        File localFileDir = new File(localFileDirPath);
        if (!localFileDir.exists()) {
            localFileDir.mkdirs();
        }
        String localFilePath = localFileDirPath + File.separator + System.currentTimeMillis() + file.getOriginalFilename();
        File localFile = new File(localFilePath);
        if (!localFile.exists()) {
            localFile.createNewFile();
        }
        file.transferTo(localFile);
        FileInputStream fis = new FileInputStream(localFile);
        //解析excel
        POIFSFileSystem pSystem = new POIFSFileSystem(fis);
        //获取整个excel
        HSSFWorkbook hb = new HSSFWorkbook(pSystem);
        System.out.println(hb.getNumCellStyles());
        //获取第一个表单sheet
        HSSFSheet sheet = hb.getSheetAt(0);
        //获取第一行
        int firstrow = sheet.getFirstRowNum();
        //获取最后一行
        int lastrow = sheet.getLastRowNum();
        //循环行数依次获取列数
        for (int i = firstrow; i < lastrow + 1; i++) {
            //获取哪一行i
            Row row = sheet.getRow(i);
            if (row != null) {
                //获取这一行的第一列
                int firstcell = row.getFirstCellNum();
                if (row.getCell(firstcell + 6) != null && row.getCell(firstcell + 7) != null && row.getCell(firstcell + 8) != null) {
                    ExamUserModel examUserModel = examUserDao.queryByExamIdAndUserId(row.getCell(firstcell + 7).toString(), row.getCell(firstcell + 6).toString());
                    if (examUserModel != null) {
                        ExamUserParam examUserParam = new ExamUserParam();
                        examUserParam.setId(examUserModel.getId());
                        examUserParam.setScore(row.getCell(firstcell + 8).toString());
                        examUserDao.update(examUserParam);
                    }


                }
//                //获取这一行的最后一列
//                int lastcell = row.getLastCellNum();
//                //创建一个集合，用处将每一行的每一列数据都存入集合中
//                List<String> list = new ArrayList<>();
//                for (int j = firstcell; j < lastcell + 1; j++) {
//                    //获取第j列
//                    Cell cell = row.getCell(j);
//
//                    if (cell != null) {
//                        System.out.print(cell + "\t");
//                        list.add(cell.toString());
//                    }
//                }
//                System.out.println("第" + i + "行" + list);
            }
        }
        fis.close();
        return "成功";
    }

}
