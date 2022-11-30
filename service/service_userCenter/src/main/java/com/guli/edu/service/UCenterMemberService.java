package com.guli.edu.service;

import com.guli.edu.entity.UCenterMember;
import com.baomidou.mybatisplus.extension.service.IService;
import com.guli.edu.entity.vo.RegisterVo;

/**
* @author anatkh
* @description 针对表【ucenter_member(会员表)】的数据库操作Service
* @createDate 2022-11-29 12:31:23
*/
public interface UCenterMemberService extends IService<UCenterMember> {

    String login(UCenterMember uCenterMember);

    void register(RegisterVo registerVo);
}
