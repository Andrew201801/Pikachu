package com.Pikachu.Dao;

import com.Pikachu.Bean.Member;


public class MemberOperationDaoImpl implements memberOperationDao {

    @Override
    public int memberAdd(Member member) {
        return 0;
    }

    @Override
    public int memberAlter(int aid, Member member) {
        return 0;
    }

    @Override
    public int memberCheck(Member member) {
        return 0;
    }

    @Override
    public int memberDelete(int aid) {
        return 0;
    }

    public Member memberQuery(int id) throws Exception {
        Member member = new Member();
//        member.setAsset("0");
        return member;
    }
}
