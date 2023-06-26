##引入mybatis支持
$!{mybatisSupport.vm}

##设置保存名称与保存位置
$!callback.setFileName($tool.append($!{tableInfo.name}, "Dao.xml"))
$!callback.setSavePath($tool.append($modulePath, "/src/main/resources/mapper"))

##拿到主键
#if(!$tableInfo.pkColumn.isEmpty())
    #set($pk = $tableInfo.pkColumn.get(0))
#end

<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="$!{tableInfo.savePackageName}.dao.$!{tableInfo.name}Dao">

    <resultMap type="$!{tableInfo.savePackageName}.pojo.entity.$!{tableInfo.name}" id="$!{tableInfo.name}Map">
#foreach($column in $tableInfo.fullColumn)
        <result property="$!column.name" column="$!column.obj.name" jdbcType="$!column.ext.jdbcType"/>
#end
    </resultMap>

    <!--查询单个-->
    <select id="queryById" resultMap="$!{tableInfo.name}Map">
        select
          #allSqlColumn()

        from $!tableInfo.obj.name
        where $!pk.obj.name = #{$!pk.name}
    </select>

    <!--查询指定行数据-->
    <select id="queryAllByLimit" resultMap="$!{tableInfo.name}Map">
        select
          #allSqlColumn()

        from $!tableInfo.obj.name
        <where>
#foreach($column in $tableInfo.fullColumn)
            <if test="param.$!column.name != null#if($column.type.equals("java.lang.String")) and param.$!column.name != ''#end">
                and $!column.obj.name = #{param.$!column.name}
            </if>
#end
        </where>
        limit #{pageable.offset}, #{pageable.pageSize}
    </select>

    <!--统计总行数-->
    <select id="count" resultType="java.lang.Long">
        select count(1)
        from $!tableInfo.obj.name
        <where>
#foreach($column in $tableInfo.fullColumn)
            <if test="$!column.name != null#if($column.type.equals("java.lang.String")) and $!column.name != ''#end">
                and $!column.obj.name = #{$!column.name}
            </if>
#end
        </where>
    </select>

    <!--新增所有列-->
    <insert id="insert" keyProperty="$!pk.name" useGeneratedKeys="true">
        insert into $!{tableInfo.obj.name}(#foreach($column in $tableInfo.otherColumn)$!column.obj.name#if($velocityHasNext), #end#end)
        values (#foreach($column in $tableInfo.otherColumn)#{param.$!{column.name}}#if($velocityHasNext), #end#end)
    </insert>

    <insert id="insertBatch" keyProperty="$!pk.name" useGeneratedKeys="true">
        insert into $!{tableInfo.obj.name}(#foreach($column in $tableInfo.otherColumn)$!column.obj.name#if($velocityHasNext), #end#end)
        values
        <foreach collection="entities" item="entity" separator=",">
        (#foreach($column in $tableInfo.otherColumn)#{entity.$!{column.name}}#if($velocityHasNext), #end#end)
        </foreach>
    </insert>

    <insert id="insertOrUpdateBatch" keyProperty="$!pk.name" useGeneratedKeys="true">
        insert into $!{tableInfo.obj.name}(#foreach($column in $tableInfo.otherColumn)$!column.obj.name#if($velocityHasNext), #end#end)
        values
        <foreach collection="entities" item="entity" separator=",">
            (#foreach($column in $tableInfo.otherColumn)#{entity.$!{column.name}}#if($velocityHasNext), #end#end)
        </foreach>
        on duplicate key update
        #foreach($column in $tableInfo.otherColumn)$!column.obj.name = values($!column.obj.name)#if($velocityHasNext),
        #end#end

    </insert>

    <!--通过主键修改数据-->
    <update id="update">
        update $!{tableInfo.obj.name}
        <set>
#foreach($column in $tableInfo.otherColumn)
            <if test="$!column.name != null#if($column.type.equals("java.lang.String")) and $!column.name != ''#end">
                $!column.obj.name = #{$!column.name},
            </if>
#end
        </set>
        where $!pk.obj.name = #{$!pk.name}
    </update>

    <!--通过主键删除-->
    <delete id="deleteById">
        delete from $!{tableInfo.obj.name} where $!pk.obj.name = #{$!pk.name}
    </delete>

</mapper>
