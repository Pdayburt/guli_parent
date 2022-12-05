package com.guli.edu.mapper;

import com.guli.edu.entity.UCenterMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

/**
* @author anatkh
* @description 针对表【ucenter_member(会员表)】的数据库操作Mapper
* @createDate 2022-11-29 12:31:23
* @Entity com.guli.edu.entity.UCenterMember
*/
public interface UCenterMemberMapper extends BaseMapper<UCenterMember> {

    int countRegister(String day);
}




