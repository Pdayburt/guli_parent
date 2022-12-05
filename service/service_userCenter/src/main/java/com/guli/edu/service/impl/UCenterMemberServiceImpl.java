package com.guli.edu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.guli.edu.commonUtils.JwtUtil;
import com.guli.edu.commonUtils.MD5;
import com.guli.edu.entity.UCenterMember;
import com.guli.edu.entity.vo.RegisterVo;
import com.guli.edu.exception.GuliException;
import com.guli.edu.service.UCenterMemberService;
import com.guli.edu.mapper.UCenterMemberMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
* @author anatkh
* @description 针对表【ucenter_member(会员表)】的数据库操作Service实现
* @createDate 2022-11-29 12:31:23
*/
@Service
public class UCenterMemberServiceImpl extends ServiceImpl<UCenterMemberMapper, UCenterMember>
    implements UCenterMemberService{

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Override
    public String login(UCenterMember uCenterMember) {

        String mobile = uCenterMember.getMobile();
        String password = uCenterMember.getPassword();
        if (StringUtils.isEmpty(mobile)||StringUtils.isEmpty(password))  throw new GuliException(20001, "手机号或者密码为空");
        LambdaQueryWrapper<UCenterMember> uCenterMemberLambdaQueryWrapper = new LambdaQueryWrapper<>();
        uCenterMemberLambdaQueryWrapper
                .eq(UCenterMember::getMobile,mobile);
        UCenterMember uCenterMemberQueried = baseMapper.selectOne(uCenterMemberLambdaQueryWrapper);
        if (uCenterMemberQueried == null) throw new GuliException(20001,"手机号不存在");
        if (!MD5.encrypt(password).equals(uCenterMemberQueried.getPassword())) throw  new GuliException(20001,"密码错误");
        if (1 == uCenterMemberQueried.getIsDisabled()) throw new GuliException(20001,"账号已被禁用");

        return JwtUtil.getJwtToken(uCenterMemberQueried.getId(), uCenterMemberQueried.getNickname());
    }

    @Override
    public void register(RegisterVo registerVo) {

        String nickName = registerVo.getNickName();
        String mobile = registerVo.getMobile();
        String password = registerVo.getPassword();
        String verificationCode = registerVo.getVerificationCode();
        if ( StringUtils.isEmpty(nickName) || StringUtils.isEmpty(mobile)
                || StringUtils.isEmpty(password)||StringUtils.isEmpty(verificationCode))
            throw new GuliException(20001, "有空值，注册失败");
        if (!verificationCode.equals(redisTemplate.opsForValue().get(mobile)))
            throw new GuliException(20001,"验证错误，注册失败");
        LambdaQueryWrapper<UCenterMember> uCenterMemberLambdaQueryWrapper = new LambdaQueryWrapper<>();
        uCenterMemberLambdaQueryWrapper
                .eq(UCenterMember::getMobile,mobile);
        Integer integer = baseMapper.selectCount(uCenterMemberLambdaQueryWrapper);
        if (integer > 0) throw new GuliException(20001,"手机账号已存在，注册失败");

        UCenterMember member = new UCenterMember();
        member.setNickname(nickName);
        member.setMobile(mobile);
        member.setPassword(MD5.encrypt(password));
        baseMapper.insert(member);

    }

    @Override
    public int countRegister(String day) {
        int num = baseMapper.countRegister(day);
        return num;
    }
}




