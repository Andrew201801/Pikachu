package com.Pikachu.Dao;

import com.Pikachu.Bean.Member;

public interface memberOperationDao {

    /*增加用户*/
    public int memberAdd(Member member) throws Exception;

    /*删除用户*/
    public int memberDelete(int aid) throws Exception;

    /*修改用户*/
    public int memberAlter(int aid,Member member) throws Exception;

    /*检查账号*/
    public int memberCheck(Member member) throws Exception;

    /*查询账号*/
    public Member memberQuery(int id) throws Exception;
}