/*
 * Copyright (c) 2020, 青软实训 and/or its affiliates. All rights reserved.
 * 青软实训 PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */

package com.zhy.es.document;


import cn.easyes.annotation.IndexField;
import cn.easyes.annotation.IndexId;
import cn.easyes.annotation.IndexName;
import cn.easyes.annotation.rely.IdType;
import lombok.Data;

/**
 * 领域模型：用户基本信息
 *
 * @author liwei
 * @since 2021-06-01 11:14:25
 */
@Data
@IndexName("member_profile")
public class MemberProfile extends SuperDomain{

    private static final long serialVersionUID = -2692133638213886759L;

    //用户id
    @IndexId(type = IdType.CUSTOMIZE)
    private String memberId;

    //邮箱
    private String email;

    //手机号
    @IndexField()
    private String mobile;

    //性别0女1男
    private Integer sex;

    //出生日期
    private Long birthday;

    //政治面貌，枚举
    private Integer politicalStatus;

    //学历
    private Integer degree;

    //学号
    private String studentCode;

    //入学时间
    private Long enrolmentAt;

    //毕业时间
    private Long graduateAt;

    //学校codetestInternshipGroupService
    private String schoolCode;

    //专业id
    private String majorCode;

    //成绩排名
    private Integer gradingRank;

    //求职状态，0求职中 1已录用
    private Integer status;

    //录用企业名称
    private String companyName;

    //录用企业id
    private String companyId;

    //录用职位名称
    private String occupationName;

    //录用职位id
    private String occupationId;

    //薪水范围（最低）
    private Integer minSalary;

    //薪水范围（最高）
    private Integer maxSalary;

    //职位/职务
    private String position;

    //是否完善基本信息
    private Integer perfected;

    /**
     * 简历是否可见 0:不可见 1:可见
     */
    private Integer visible;

    /**
     * 在线简历或者附件简历更新时间
     */
    private Long resumeUpdateAt;

    // 简历最高完善度0-100
    private Integer completion;

    // 简历最高完成时间
    private Long completionAt;

    //备注
    private String remark;

}
