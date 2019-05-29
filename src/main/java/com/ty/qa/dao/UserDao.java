package com.ty.qa.dao;

import com.ty.qa.model.User;
import org.apache.ibatis.annotations.*;

/**
 * created by TY on 2019/5/3.
 */
@Mapper
public interface UserDao {
    //两边都加空格
    String TABLE_NAME = " user ";
    String INSERT_FIELDS = " name, password, salt, head_url ";
    String SELECT_FIELDS = " id, " + INSERT_FIELDS;
    //@Insert({"insert into", TABLE_NAME, "(name, password, salt, headUrl) values() "})
    @Insert({"insert into", TABLE_NAME, "(", INSERT_FIELDS,
            ") values(#{name}, #{password}, #{salt}, #{headUrl})"})
    int addUser(User user);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where id=#{id}"})
    User selectByid(int id);

    @Select({"select ", SELECT_FIELDS, " from ", TABLE_NAME, " where name=#{name}"})
    User selectByName(String name);

    @Update({"update ", TABLE_NAME, " set password=#{password} where id =#{id}"})
    void updatePassword(User user);

    @Delete({"delete from ", TABLE_NAME, " where id=#{id}"})
    void deleteById(int id);
}
