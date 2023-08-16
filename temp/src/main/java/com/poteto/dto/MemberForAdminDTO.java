package com.poteto.dto;

import com.poteto.entity.MemberEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter //Dto->Json을 하는 @ResponseBody가 사용
@NoArgsConstructor
public class MemberForAdminDTO {

    private String memberId;
    private String memberName;
    private String memberPhone;
    private String memberEmail;

    public MemberForAdminDTO(MemberEntity memberEntity){
        this.memberId = memberEntity.getMemberId();
        this.memberName = memberEntity.getMemberName();
        this.memberPhone = memberEntity.getMemberPhone();
        this.memberEmail = memberEntity.getMemberEmail();
    }
}