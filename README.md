# 基于NYHA心功能分级的心力衰竭患者随访信息管理系统

## 功能设计

患者

>1.患者能够通过随访系统进行选择医生进行随访
>
>2.患者可以选择住院



医生

>1.医生可以填写就诊内容
>
>2.医生可以同意患者的随访申请



护士

>1.护士能够接受随访内容



医院

>1.医院可以看所有消息



## 数据库设计

### t_doctor(医生表)(不需要)

>#id，医生名字，医生年龄，医生所属医院，医生职级，医生电话，医生邮箱，医生上班时间，医生主要研究方向（专长），备注，is_state，创建时间，创建人id，更新时间，更新人id，租户id
>
>| 表单 | id     | name      | age  | hospital   | rank | phonenumber | email     | work_hours | expertise  | remark     | is_state | create_time | create_id | update_time | update_id | tenant_id |
>| ---- | ------ | --------- | ---- | ---------- | ---- | ----------- | --------- | ---------- | ---------- | ---------- | -------- | ----------- | --------- | ----------- | --------- | --------- |
>| 参数 | Bigint | varchar30 | Int  | Varchar150 | Int  | varchar20   | Varchar30 | Varchar50  | Varchar255 | Varchar500 | Int      | Date        | Bigint    | Date        | Bigint    | Bigint    |



### t_nurse(护士表)(不需要)

>#id，护士名字，护士年龄，护士所属医院，护士职级，护士电话，护士邮箱，护士上班时间，备注，is_state，创建时间，创建人id，更新时间，更新人id，租户id
>
>| 表单 | id     | name      | age  | hospital   | rank | phonenumber | email     | work_hours | remark     | is_state | create_time | create_id | update_time | update_id | tenant_id |
>| ---- | ------ | --------- | ---- | ---------- | ---- | ----------- | --------- | ---------- | ---------- | -------- | ----------- | --------- | ----------- | --------- | --------- |
>| 参数 | Bigint | varchar30 | Int  | Varchar150 | Int  | varchar20   | Varchar30 | Varchar50  | Varchar500 | Int      | Date        | Bigint    | Date        | Bigint    | Bigint    |



### t_hospital(医院表)

>#id，医院名字，医院地址，医院邮箱，医院电话，医院等级，医院备注，is_state，创建时间，创建人id，更新时间，更新人id，租户id
>
>| 表单 | id     | name      | address    | email     | phonenumber | level | remark     | is_state | create_time | create_id | update_time | update_id | tenant_id |
>| ---- | ------ | --------- | ---------- | --------- | ----------- | ----- | ---------- | -------- | ----------- | --------- | ----------- | --------- | --------- |
>| 参数 | Bigint | Varchar30 | Varchar255 | Varchar30 | Varchar20   | int   | Varchar500 | Int      | Date        | Bigint    | Date        | Bigint    | Bigint    |



### t_visit(就诊表)

>#id，就诊患者id，就诊患者名字，医生id，医生名字，护士1id，护士1名字，护士2id，护士2名字，护士数量，就诊日期，就诊的症状体征，实验室检查结果，化验单图片路径，用药的依从性（是否调整用药），备注，最近有无诱因，有无再入院，心理状态，生活方式，is_state，NYHA心功能分级评估，创建时间，创建人id，更新时间，更新人id，租户id
>
>| 表单 | id     | patient_id | patient_name | doctor_id | doctor_name | nurse1_id | nurse1_name | nurse2_id | nurse2_name | nurse_number | visit_date | symptoms  | results    | picture_url | compliance | remark     | incentive  | in_hospital | psychology | life_style | is_state | nyha_level | is_state | create_time | create_id | update_time | update_id | tenant_id |
>| ---- | ------ | ---------- | ------------ | --------- | ----------- | --------- | ----------- | --------- | ----------- | ------------ | ---------- | --------- | ---------- | ----------- | ---------- | ---------- | ---------- | ----------- | ---------- | ---------- | -------- | ---------- | -------- | ----------- | --------- | ----------- | --------- | --------- |
>| 参数 | Bigint | Bigint     | Varchar20    | Bigint    | Varchar20   | Bigint    | Varchar20   | Bigint    | Varchar20   | Bigint       | Date       | Varchar50 | Varchar255 | varchar500  | int        | Varchar500 | varchar100 | Int         | Varchar255 | varchar500 | int      | varchar500 | Int      | Date        | Bigint    | Date        | Bigint    | Bigint    |



### t_follow(随访表)

>#id，患者id，患者名字，医生id，医生名字，时间，就诊id，创建时间，创建人id，更新时间，更新人id，租户id
>
>| 表单 | id     | patient_id | patient_name | doctor_id | doctor_name | date | visit_id | is_state | create_time | create_id | update_time | update_id | tenant_id |
>| ---- | ------ | ---------- | ------------ | --------- | ----------- | ---- | -------- | -------- | ----------- | --------- | ----------- | --------- | --------- |
>| 参数 | Bigint | Bigint     | varchar20    | Bigint    | Varchar20   | Date | Bigint   | Int      | Date        | Bigint    | Date        | Bigint    | Bigint    |



