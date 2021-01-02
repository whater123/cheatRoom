package com.my.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.pojo.base.User;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

/**
 * @author w
 */
@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("select user_password from user where user_name = #{userName}")
    String getPassword(String userName);

    @Select("select user_role from user where user_name = #{userName}")
    String getRole(String userName);

    @Update("update user set user_head_name=#{headName} where user_name=#{userName}")
    void setHeadName(String headName,String userName);
}
