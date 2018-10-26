package com.Pikachu.Service;

import com.Pikachu.Bean.Member;

public interface MemberOperationService {

    public boolean memberAdd(Member member) throws Exception;

    public boolean memberDelete(int id) throws Exception;

    public boolean memberAlter(int id, Member member) throws Exception;

    public int memberCheck(Member member) throws Exception;

    public Member memberQuery(int id) throws Exception;
}
