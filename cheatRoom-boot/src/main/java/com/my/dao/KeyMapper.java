package com.my.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.my.pojo.base.Key;
import com.my.pojo.base.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author w
 */
@Repository
public interface KeyMapper extends BaseMapper<Key> {
    @Insert("INSERT INTO `key` (`key_create_time`, `key_context`) VALUES (#{keyCreateTime}, #{keyContext});")
    int insertKey(Key key);

    @Select("select * from `key`")
    List<Key> getAllKeys();

    @Update("update `key` set user_id=#{userId},key_use_time=#{keyUseTime} where key_id=#{keyId}")
    int useKey(Key key);

    @Select("select * from `key` where key_context=#{key}")
    Key getKeyBtName(String key);
}
