create database cross_fit;
USE CROSS_FIT;

CREATE TABLE `Member`
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

CREATE TABLE PRODUCT
(
    productID   INT auto_increment primary KEY,
    productType CHAR(1) NOT NULL
);

CREATE TABLE SavingsProduct
(
    productID     INT PRIMARY KEY,
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
    FOREIGN KEY (ProductID) REFERENCES Product (ProductID)
);

CREATE TABLE SavingProductRates
(
    productID         INT,
    save_trm          INT,
    intr_rate_type    CHAR(1),
    intr_rate_type_nm VARCHAR(20),
    intr_rate         DECIMAL(5, 2),
    intr_rate2        DECIMAL(5, 2),
    rsrv_type         VARCHAR(10),
    rsrv_type_nm      VARCHAR(50),
    PRIMARY KEY (productID, save_trm, rsrv_type),
    FOREIGN KEY (productID) REFERENCES SavingsProduct (productID)
);

CREATE TABLE FundProduct
(
    productID         INT NOT NULL,   -- 금융상품 테이블의 외래키
    company_nm        VARCHAR(100),   -- 운용사명
    product_nm        VARCHAR(255),   -- 상품명
    yield_1           DECIMAL(15, 2), -- 1개월 누적수익률
    yield_3           DECIMAL(15, 2), -- 3개월 누적수익률
    yield_6           DECIMAL(15, 2), -- 6개월 누적수익률
    yield_12          DECIMAL(15, 2), -- 12개월 누적수익률
    RiskLevel         INT,            -- 펀드 등급
    fund_type         VARCHAR(50),    -- 펀드 유형
    advanced_fee      DECIMAL(15, 2), -- 선취수수료
    total_payoff_rate DECIMAL(15, 2), -- 총 보수율
    hit               INT,            -- 조회수
    PRIMARY KEY (ProductID),          -- Primary Key 설정
    FOREIGN KEY (ProductID) REFERENCES Product (ProductID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE BondProduct
(
    productID         INT NOT NULL,  -- 상품ID (Product 테이블의 외래키)
    basDt             VARCHAR(8),    -- 기준일자
    crno              VARCHAR(13),   -- 법인등록번호
    scrsItmsKcd       VARCHAR(4),    -- 유가증권종목코드
    isinCd            VARCHAR(12),   -- ISIN코드
    bondIsurNm        VARCHAR(100),  -- 채권발행인명
    isinCdNm          VARCHAR(100),  -- ISIN코드명
    bondIssuDt        VARCHAR(8),    -- 채권발행일자
    bondIssuAmt       BIGINT,        -- 채권발행금액
    bondIssuCurCd     VARCHAR(3),    -- 채권발행통화코드
    bondIssuCurCdNm   VARCHAR(50),   -- 채권발행통화코드명
    bondExprDt        VARCHAR(8),    -- 채권만기일자
    bondPymtAmt       BIGINT,        -- 채권납입금액
    irtChngDcd        CHAR(1),       -- 금리변동구분코드
    irtChngDcdNm      VARCHAR(50),   -- 금리변동구분코드명
    bondSrfcInrt      DECIMAL(5, 2), -- 채권금리
    bondIntTcd        CHAR(1),       -- 채권이자형구분코드
    bondIntTcdNm      VARCHAR(50),   -- 채권이자형구분코드명
    intPayCyclCtt     VARCHAR(50),   -- 이자지급주기내용
    nxtmCopnDt        VARCHAR(8),    -- 차기표일자
    kbpScrsItmsKcdNm  VARCHAR(100),  -- 한국신용평가유가증권종목종류코드명
    niceScrsItmsKcdNm VARCHAR(100),  -- NICE평가정보유가증권종목종류코드명
    fnScrsItmsKcdNm   VARCHAR(100),  -- FN유가증권종목종류코드명
    clprPrc           DECIMAL(10, 2),-- 채권 종가
    hit               INT DEFAULT 0, -- 조회수, 기본값 0
    PRIMARY KEY (productID),         -- Primary Key 설정
    FOREIGN KEY (productID) REFERENCES Product (productID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE `stock`
(
    `stockCode`  VARCHAR(10)    NOT NULL, -- 주식 코드
    `stockName`  VARCHAR(50)    NULL,     -- 주식 이름
    `dailyPrice` DECIMAL(10, 2) NULL,     -- 일일 가격
    `field`      INT            NULL,     -- 분야 코드
    `itmsNm`     VARCHAR(50)    NULL,     -- 항목 이름
    `mrktCtg`    VARCHAR(50)    NULL,     -- 시장 카테고리
    `clpr`       INT            NULL,     -- 종가
    `vs`         INT            NULL,     -- 등락폭
    `fltRt`      DECIMAL(5, 2)  NULL,     -- 등락률
    `mkp`        INT            NULL,     -- 시가
    `hipr`       INT            NULL,     -- 최고가
    `lopr`       INT            NULL,     -- 최저가
    `trqu`       BIGINT         NULL,     -- 거래량
    `trPrc`      BIGINT         NULL,     -- 거래대금
    `istgStCnt`  BIGINT         NULL,     -- 상장 주식 수
    `mrktTotAmt` BIGINT         NULL,     -- 시장 총액
    PRIMARY KEY (`stockCode`)             -- stockCode를 기본 키로 설정
);

CREATE TABLE PreferenceProductHits
(
    hit_num    INT auto_increment PRIMARY KEY,
    productID  INT,
    preference INT,
    HIT        INT NOT NULL,
    foreign key (productID) references Product (productID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Youtube
(
    youtube_num     INT PRIMARY KEY auto_increment,
    youtube_url     VARCHAR(255),
    youtube_title   VARCHAR(255),
    youtube_context TEXT,
    reg_date        DATETIME DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE `Portfolio`
(
    `portfolioID`    INT             NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `portfolioName`  VARCHAR(100)    NULL,
    `creationDate`   DATETIME        NULL,
    `total`          DECIMAL(15, 2)  NULL,
    `expectedReturn` DECIMAL(5, 2)   NULL,
    `riskLevel`      DECIMAL(5, 2)   NULL,
    `memberNum`      BIGINT UNSIGNED NOT NULL
);

CREATE TABLE `PortfolioItem`
(
    `portfolioItemID` INT               NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `portfolioID`     INT               NOT NULL,
    `productID`       INT               NULL,
    `stockCode`       VARCHAR(10)       NULL,
    `amount`          DECIMAL(15, 2)    NULL,
    `expectedReturn`  DECIMAL(5, 2)     NULL,
    `riskLevel`       INT               NULL,
    `productType`     CHAR(1)           NULL,
    FOREIGN KEY (`portfolioID`) REFERENCES `portfolio` (`portfolioID`) ON DELETE CASCADE ON UPDATE CASCADE
);


INSERT INTO `Portfolio` (`portfolioName`, `creationDate`, `total`, `expectedReturn`, `riskLevel`, `memberNum`)
VALUES ('Portfolio 1', NOW(), 2500000, 5.50, 3, 1),
       ('Portfolio 2', NOW(), 4500000, 6.20, 2, 2),
       ('Portfolio 3', NOW(), 6700000, 4.75, 4, 1),
       ('Portfolio 4', NOW(), 8900000, 5.10, 5, 2),
       ('Portfolio 5', NOW(), 3200000, 7.00, 1, 1);

INSERT INTO `PortfolioItem` (`portfolioID`, `productID`, `stockCode`, `amount`, `expectedReturn`, `riskLevel`, `productType`)
VALUES (1, NULL, '000020', 7, 5.10, 1, NULL),
       (1, 1001, NULL, 100000, 6.00, 2, 'S'),
       (1, NULL, '000040', 3, 4.80, 3, NULL),
       (1, 1002, NULL, 150000, 5.50, 3, 'F'),
       (2, 1003, NULL, 120000, 6.30, 4, 'B'),
       (2, NULL, '000050', 9, 7.20, 3, NULL),
       (2, 1004, NULL, 180000, 6.10, 4, 'F'),
       (2, NULL, '000070', 5, 5.40, 3, NULL),
       (3, NULL, '000075', 4, 5.00, 3, NULL),
       (3, 1005, NULL, 130000, 7.00, 1, 'F'),
       (3, NULL, '000080', 8, 6.80, 2, NULL),
       (3, 1006, NULL, 110000, 5.90, 3, 'F'),
       (4, 1007, NULL, 140000, 5.50, 3, 'F'),
       (4, NULL, '000087', 6, 6.10, 1, NULL),
       (4, 1008, NULL, 160000, 7.50, 5, 'S'),
       (4, NULL, '000100', 2, 4.90, 6, NULL),
       (5, NULL, '000105', 5, 5.60, 1, NULL),
       (5, 1009, NULL, 170000, 6.70, 2, 'F'),
       (5, NULL, '000120', 8, 4.70, 5, NULL),
       (5, 1010, NULL, 190000, 7.20, 2, 'B');

CREATE TABLE `CartItem`
(
    `cartID`         INT           NOT NULL AUTO_INCREMENT PRIMARY KEY,
    `productID`      INT           NOT NULL,
    `memberNum`      INT           NOT NULL,
    `productType`    CHAR(1)       NOT NULL,
    `provider`       VARCHAR(100)  NULL,
    `productName`    VARCHAR(100)  NOT NULL,
    `expectedReturn` DECIMAL(5, 2) NULL,
    `rsrvType`       VARCHAR(10)   NULL
);

INSERT INTO `CartItem` (`cartID`, `productID`, `memberNum`, `productType`, `provider`, `productName`, `expectedReturn`, `rsrvType`)
VALUES (1, 1, 1, 'S', 'Provider A', 'Savings Product 1', 3.50, 'S'),
       (2, 12, 2, 'F', 'Provider B', 'Fund Product 1', 2.75, Null),
       (3, 14, 1, 'B', 'Provider C', 'Bond Product 1', 4.20, Null),
       (4, 74, 2, 'S', 'Provider D', 'Cash Product 1', 1.50, Null),
       (5, 165, 1, 'S', 'Provider E', 'Savings Product 2', 2.80, Null),
       (6, 63, 2, 'F', 'Provider F', 'Fund Product 2', 3.10, Null),
       (7, 107, 1, 'B', 'Provider G', 'Bond Product 2', 3.95, Null),
       (8, 8, 2, 'S', 'Provider H', 'Cash Product 2', 0.85, 'F'),
       (9, 91, 1, 'S', 'Provider I', 'Savings Product 3', 4.50, 'S'),
       (10, 10, 2, 'F', 'Provider J', 'Fund Product 3', 2.90, Null);

CREATE TABLE `Insight`
(
    `insightID` INT          NOT NULL,
    `type`      VARCHAR(20)  NULL,
    `content`   TEXT         NULL,
    `URL`       VARCHAR(255) NULL
);

CREATE TABLE AgeGroupProductHits (
                                     hit_num INT auto_increment primary KEY,
                                     productID INT,
                                     age_group INT NOT NULL,
                                     HIT INT NOT NULL,
                                     FOREIGN KEY (productID) REFERENCES Product(productID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE PreferenceProductHits (
                                       hit_num INT auto_increment PRIMARY KEY,
                                       productID INT,
                                       preference INT,
                                       HIT INT NOT NULL,
                                       foreign key (productID) references Product(productID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Youtube (
                         youtube_num INT PRIMARY KEY auto_increment,
                         youtube_url VARCHAR(255),
                         youtube_title VARCHAR(255),
                         youtube_context TEXT,
                         reg_date DATETIME DEFAULT CURRENT_TIMESTAMP
);

-- 페르소나 테이블
CREATE TABLE persona (
                         persona_id INT NOT NULL AUTO_INCREMENT,   -- 기본키, 자동 증가
                         persona_preference INT,                   -- 투자 성향
                         stock_rate INT,                           -- 주식 비율
                         fund_rate INT,                            -- 펀드 비율
                         bond_rate INT,                            -- 채권 비율
                         savings_rate INT,                         -- 예적금 비율
                         persona_name VARCHAR(50),                 -- 페르소나 이름
                         job VARCHAR(255),                         -- 직업
                         comments VARCHAR(255),
                         PRIMARY KEY (persona_id)                  -- 기본키 설정
);

INSERT INTO persona (persona_id, persona_preference, stock_rate, fund_rate, bond_rate, savings_rate, persona_name, job, comments)
VALUES
    (1, 4, 50, 20, 10, 5, 'Warren Buffett', '가치투자의 대가, CEO', '가격은 당신이 지불하는 것이고, 가치는 당신이 얻는 것이다.'),
    (2, 5, 70, 15, 10, 5, 'George Soros', '매크로 분석의 귀재, 펀드매니저','당신이 맞는지 틀리는지가 중요한 것이 아니라, 당신이 맞을 때 얼마나 많은 돈을 버는지와 틀릴 때 얼마나 많은 돈을 잃는지가 중요하다.'),
    (3, 4, 30, 20, 40, 10, 'Ray Dalio', '헤지펀드 매니저','공정함을 추구하는 사람은 공정함을 실천해야 한다.'),
    (4, 4, 50, 20, 20, 10, '이채원', '대한민국 가치투자 대부, 라이프자산운용 의장', '투자는 과거의 데이터를 바탕으로 미래를 예측하는 것이기 때문에, 우리가 겪는 모든 경험은 투자에 큰 도움이 된다.'),
    (5, 5, 70, 15, 10, 5, '김범석', '쿠팡 CEO', ''),
    (6, 4, 40, 20, 30, 10, '강방천', '에셋플러스자산운용 CIO', '위험을 관리하는 것은 성공적인 투자의 핵심이다.');

ALTER TABLE persona ADD image_path VARCHAR(255);

UPDATE persona
SET image_path = 'crossfit_images/id_1.jpg'
WHERE persona_id = 1;

UPDATE persona
SET image_path = 'crossfit_images/id_2.jpg'
WHERE persona_id = 2;

UPDATE persona
SET image_path = 'crossfit_images/id_3.jpg'
WHERE persona_id = 3;

UPDATE persona
SET image_path = 'crossfit_images/id_4.jpg'
WHERE persona_id = 4;

UPDATE persona
SET image_path = 'crossfit_images/id_5.jpg'
WHERE persona_id = 5;

UPDATE persona
SET image_path = 'crossfit_images/id_6.jpg'
WHERE persona_id = 6;