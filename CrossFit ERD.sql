use crossfit_mysql;

create table member
(
    `member_num`   BIGINT UNSIGNED NOT NULL auto_increment primary key,
    `member_id`    varchar(100)    NOT NULL unique,
    `member_name`  VARCHAR(50)     NOT NULL,
    `email`        VARCHAR(100)    NOT NULL unique,
    `password`     VARCHAR(255)    NOT NULL,
    `birth`        DATE            NULL,
    `preference`   INT             NULL,
    `invest_score` INT             NULL,
    `reg_Date`     DATETIME        NULL,
    `gender`       VARCHAR(10)     NULL
);

create table member_role
(
    member_role_id bigint unsigned primary key auto_increment not null,
    role           varchar(10)                                not null,
    member_num     bigint unsigned,
    foreign key (member_num) references member (member_num)
);

CREATE TABLE product
(
    product_id   INT auto_increment primary KEY,
    product_type CHAR(1) NOT NULL
);

CREATE TABLE saving_product
(
    product_id     INT PRIMARY KEY,
    fin_co_no     VARCHAR(50),
    fin_prdt_cd   VARCHAR(50),
    kor_co_nm     VARCHAR(100),
    fin_prdt_nm   VARCHAR(100),
    dcls_month    VARCHAR(6),
    join_way      TEXT,
    mtrt_int      TEXT,
    spcl_cnd      TEXT,
    join_deny     INT,
    join_member   VARCHAR(100),
    etc_note      TEXT,
    max_limit     VARCHAR(255),
    dcls_strt_day VARCHAR(8),
    dcls_end_day  VARCHAR(8),
    hit           INT DEFAULT 0,
    FOREIGN KEY (product_id) REFERENCES product (product_id)
);

CREATE TABLE saving_product_rate
(
    product_id         INT,
    save_trm          INT,
    intr_rate_type    CHAR(1),
    intr_rate_type_nm VARCHAR(20),
    intr_rate         DECIMAL(5, 2),
    intr_rate2        DECIMAL(5, 2),
    rsrv_type         VARCHAR(10),
    rsrv_type_nm      VARCHAR(50),
    PRIMARY KEY (product_id, save_trm, rsrv_type),
    FOREIGN KEY (product_id) REFERENCES saving_product (product_id)
);

CREATE TABLE fund_product
(
    product_id         INT NOT NULL,   -- 금융상품 테이블의 외래키
    company_nm        VARCHAR(100),   -- 운용사명
    product_nm        VARCHAR(255),   -- 상품명
    yield_1           DECIMAL(15, 2), -- 1개월 누적수익률
    yield_3           DECIMAL(15, 2), -- 3개월 누적수익률
    yield_6           DECIMAL(15, 2), -- 6개월 누적수익률
    yield_12          DECIMAL(15, 2), -- 12개월 누적수익률
    risk_level         INT,            -- 펀드 등급
    fund_type         VARCHAR(50),    -- 펀드 유형
    advanced_fee      DECIMAL(15, 2), -- 선취수수료
    total_payoff_rate DECIMAL(15, 2), -- 총 보수율
    hit               INT,            -- 조회수
    PRIMARY KEY (product_id),          -- Primary Key 설정
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE bond_product
(
    product_id         INT NOT NULL,  -- 상품ID (Product 테이블의 외래키)
    bas_dt             VARCHAR(8),    -- 기준일자
    crno              VARCHAR(13),   -- 법인등록번호
    scrs_itms_kcd       VARCHAR(4),    -- 유가증권종목코드
    isin_cd            VARCHAR(12),   -- ISIN코드
    bond_isur_nm        VARCHAR(100),  -- 채권발행인명
    isin_cd_nm          VARCHAR(100),  -- ISIN코드명
    bond_issu_dt        VARCHAR(8),    -- 채권발행일자
    bond_issu_amt       BIGINT,        -- 채권발행금액
    bond_issu_curCd     VARCHAR(3),    -- 채권발행통화코드
    bond_issu_cur_cd_nm   VARCHAR(50),   -- 채권발행통화코드명
    bond_expr_dt        VARCHAR(8),    -- 채권만기일자
    bond_pymt_amt       BIGINT,        -- 채권납입금액
    irt_chng_dcd        CHAR(1),       -- 금리변동구분코드
    irt_chng_dcd_nm      VARCHAR(50),   -- 금리변동구분코드명
    bond_srfc_inrt      DECIMAL(5, 2), -- 채권금리
    bond_int_tcd        CHAR(1),       -- 채권이자형구분코드
    bond_int_tcdNm      VARCHAR(50),   -- 채권이자형구분코드명
    int_pay_cycl_ctt     VARCHAR(50),   -- 이자지급주기내용
    nxtm_copn_dt        VARCHAR(8),    -- 차기이표일자
    kbp_scrs_itms_kcd_nm  VARCHAR(100),  -- 한국신용평가유가증권종목종류코드명
    nice_scrs_itms_kcd_nm VARCHAR(100),  -- NICE평가정보유가증권종목종류코드명
    fn_scrs_itms_kcdNm   VARCHAR(100),  -- FN유가증권종목종류코드명
    clpr_prc           DECIMAL(10, 2),-- 채권 종가
    hit               INT DEFAULT 0, -- 조회수, 기본값 0
    PRIMARY KEY (product_id),         -- Primary Key 설정
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `stock`
(
    `stock_code`  VARCHAR(10)    NOT NULL, -- 주식 코드
    `stock_name`  VARCHAR(50)    NULL,     -- 주식 이름
    `daily_price` DECIMAL(10, 2) NULL,     -- 일일 가격
    `field`      INT            NULL,     -- 분야 코드
    `itms_nm`     VARCHAR(50)    NULL,     -- 항목 이름
    `mrkt_ctg`    VARCHAR(50)    NULL,     -- 시장 카테고리
    `clpr`       INT            NULL,     -- 종가
    `vs`         INT            NULL,     -- 등락폭
    `flt_rt`      DECIMAL(5, 2)  NULL,     -- 등락률
    `mkp`        INT            NULL,     -- 시가
    `hipr`       INT            NULL,     -- 최고가
    `lopr`       INT            NULL,     -- 최저가
    `trqu`       BIGINT         NULL,     -- 거래량
    `tr_prc`      BIGINT         NULL,     -- 거래대금
    `istg_st_cnt`  BIGINT         NULL,     -- 상장 주식 수
    `mrkt_tot_amt` BIGINT         NULL,     -- 시장 총액
    PRIMARY KEY (`stock_code`)             -- stockCode를 기본 키로 설정
);

CREATE TABLE `portfolio`
(
    `portfolio_id`    INT                                NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `portfolio_name`  VARCHAR(100)                       NULL,    -- 포트폴리오 이름
    `creation_date`   DATETIME DEFAULT CURRENT_TIMESTAMP NULL,    -- 생성일
    `total`          DECIMAL(15, 2)                     NULL,    -- 투자총액
    `expected_return` DECIMAL(5, 2)                      NULL,    -- 기대수익률
    `risk_level`      DECIMAL(5, 2)                      NULL,    -- 위험도
    `member_num`      BIGINT UNSIGNED                    NOT NULL -- 사용자고유번호
);

CREATE TABLE `portfolio_item`
(
    `portfolio_item_id` INT            NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `portfolio_id`     INT            NOT NULL,
    `product_id`       INT            NULL, -- 상품ID
    `stock_code`       VARCHAR(10)    NULL, -- 주식코드
    `amount`          DECIMAL(15, 2) NULL, -- 투자금액
    `expected_return`  DECIMAL(5, 2)  NULL, -- 기대수익률
    `risk_level`       INT            NULL, -- 위험도
    `product_type`     CHAR(1)        NULL, -- 상품종류
    FOREIGN KEY (`portfolio_id`) REFERENCES `portfolio` (`portfolio_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`stock_code`) REFERENCES `stock` (`stock_code`) ON UPDATE CASCADE,
    FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON UPDATE CASCADE
);

CREATE TABLE `cart_item`
(
    `cart_id`         INT             NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `product_id`      INT             NOT NULL, -- 상품ID
    `member_num`      BIGINT UNSIGNED NOT NULL, -- 사용자고유번호
    `product_type`    CHAR(1)         NOT NULL, -- 상품종류
    `provider`       VARCHAR(100)    NULL,     -- 제공자
    `product_name`    VARCHAR(100)    NOT NULL, -- 상품명
    `expected_return` DECIMAL(5, 2)   NULL,     -- 수익률
    `rsrv_type`       VARCHAR(10)     NULL,     -- 적립유형
    FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (`member_num`) REFERENCES `member` (`member_num`) ON DELETE CASCADE ON UPDATE CASCADE
);

-- CREATE TABLE `CartItem`
-- (
--     `cartID`         INT             NOT NULL AUTO_INCREMENT PRIMARY KEY,
--     `productID`      INT             NOT NULL, -- 상품ID
--     `memberNum`      BIGINT UNSIGNED NOT NULL, -- 사용자고유번호
--     FOREIGN KEY (`productID`) REFERENCES `product` (`productID`) ON DELETE CASCADE ON UPDATE CASCADE,
--     FOREIGN KEY (`memberNum`) REFERENCES `member` (`member_Num`) ON DELETE CASCADE ON UPDATE CASCADE
-- );

CREATE TABLE age_group_product_hits
(
    hit_num   INT auto_increment primary KEY,
    product_id INT,
    age_group INT NOT NULL,
    hit       INT NOT NULL,
    FOREIGN KEY (product_id) REFERENCES product (product_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE preference_product_hits
(
    hit_num    INT auto_increment PRIMARY KEY,
    product_id  INT,
    preference INT,
    hit        INT NOT NULL,
    foreign key (product_id) references product (product_id) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE youtube
(
    youtube_num     INT PRIMARY KEY auto_increment,
    youtube_url     VARCHAR(255),
    youtube_title   VARCHAR(255),
    youtube_context TEXT,
    reg_date        DATETIME DEFAULT CURRENT_TIMESTAMP
);

# INSERT INTO youtube(youtube_url, youtube_title, youtube_context) VALUES
#     ("https://www.youtube.com/watch?v=qhzcjT7uF18", "포트폴리오 관점에서 보는 '공격적인 투자'",
#      "여기에 md 파일 열어서 전체 선택해서 ctrl + v 해서 넣으세요")
#       md파일 자료는 노션 백엔드팀 제일 밑에 있습니다.

-- 페르소나 테이블
CREATE TABLE persona
(
    persona_id         INT NOT NULL AUTO_INCREMENT PRIMARY KEY, -- 기본키, 자동 증가
    persona_preference INT,                                     -- 투자 성향
    stock_rate         INT,                                     -- 주식 비율
    fund_rate          INT,                                     -- 펀드 비율
    bond_rate          INT,                                     -- 채권 비율
    savings_rate       INT,                                     -- 예적금 비율
    persona_name       VARCHAR(50),                             -- 페르소나 이름
    job                VARCHAR(255),                            -- 직업
    comments           VARCHAR(255),                            -- 명언
    image_path         VARCHAR(255)                             -- 사진 경로
);

INSERT INTO persona (persona_id, persona_preference, stock_rate, fund_rate, bond_rate, savings_rate, persona_name, job,
                     comments, image_path)
VALUES (1, 4, 50, 20, 10, 5, 'Warren Buffett', '가치투자의 대가, CEO', '가격은 당신이 지불하는 것이고, 가치는 당신이 얻는 것이다.',
        'crossfit_images/id_1.jpg'),
       (2, 5, 70, 15, 10, 5, 'George Soros', '매크로 분석의 귀재, 펀드매니저',
        '당신이 맞는지 틀리는지가 중요한 것이 아니라, 당신이 맞을 때 얼마나 많은 돈을 버는지와 틀릴 때 얼마나 많은 돈을 잃는지가 중요하다.', 'crossfit_images/id_2.jpg'),
       (3, 4, 30, 20, 40, 10, 'Ray Dalio', '헤지펀드 매니저', '공정함을 추구하는 사람은 공정함을 실천해야 한다.', 'crossfit_images/id_3.jpg'),
       (4, 4, 50, 20, 20, 10, '이채원', '대한민국 가치투자 대부, 라이프자산운용 의장',
        '투자는 과거의 데이터를 바탕으로 미래를 예측하는 것이기 때문에, 우리가 겪는 모든 경험은 투자에 큰 도움이 된다.', 'crossfit_images/id_4.jpg'),
       (5, 5, 70, 15, 10, 5, '김범석', '쿠팡 CEO', null, 'crossfit_images/id_5.jpg'),
       (6, 4, 40, 20, 30, 10, '강방천', '에셋플러스자산운용 CIO', '위험을 관리하는 것은 성공적인 투자의 핵심이다.', 'crossfit_images/id_6.jpg'),
       (7, 3, 25, 30, 20, 25, '원빈', 'RIIZE 멤버', null, 'crossfit_images/id_7.jpg'),
       (8, 4, 30, 30, 20, 20, '장원영', '아이브(IVE) 멤버', null, 'crossfit_images/id_8.jpg'),
       (9, 1, 15, 20, 30, 35, '송혜교', '배우', null, 'crossfit_images/id_9.jpg'),
       (10, 2, 25, 20, 30, 25, '김구라', '예능인', null, 'crossfit_images/id_10.jpg');