package org.example.controller.exam;

import org.example.common.Result;
import org.example.common.Utils.EnumUtil;
import org.example.common.enums.EnumEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 枚举访问
 *
 * @author Jiankun
 * @since 2023-06-29 00:54:34
 */
@RestController
@RequestMapping("/enum")
@CrossOrigin
public class EnumController {

    /**
     * 本方法通过枚举类名进行访问枚举
     *
     * @param enumName  枚举的类名
     * @return
     */
    @GetMapping("/{enumName}")
    public Result enumerationAccess(@PathVariable("enumName") String enumName) {
        List<EnumEntity> enumEntities = EnumUtil.getByName(enumName);
        return Result.ok(enumEntities);
    }
}
