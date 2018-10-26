package com.Pikachu.Service;

import com.Pikachu.Bean.Member;

public class MemberOperationServiceImpl implements MemberOperationService {
    @Override
    public boolean memberAdd(Member member) {
        return false;
    }

    @Override
    public boolean memberDelete(int id) {
        return false;
    }

    @Override
    public boolean memberAlter(int id, Member member) {
        return false;
    }

    @Override
    public int memberCheck(Member member) {
        return 0;
    }

    @Override
    public Member memberQuery(int id) {
        return null;
    }
}
