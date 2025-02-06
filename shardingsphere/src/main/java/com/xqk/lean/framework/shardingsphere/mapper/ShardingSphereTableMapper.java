package com.xqk.lean.framework.shardingsphere.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * ShardingSphereTableMapper
 *
 * @author qiankun.xiong
 * @version 1.0.0
 * @since 2025/2/6 14:55
 */
@Mapper
public interface ShardingSphereTableMapper{
    /**
     * 根据ID查找名称
     *
     * @param id id
     * @return name
     */
    String selectNameById(Long id);

    /**
     * 根据ID查找名称
     *
     * @param id id
     * @return name
     */
    @Select("SELECT name FROM shardingsphere.sharding_sphere_table WHERE id = #{id}")
    String selectNameById2(@Param("id") Long id);
}
