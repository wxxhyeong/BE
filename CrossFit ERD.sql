create database cross_fit;
USE CROSS_FIT;
CREATE TABLE `User` (
	`userNum`	INT	NOT NULL,
	`username`	VARCHAR(50)	NULL,
	`email`	VARCHAR(100)	NULL,
	`password`	VARCHAR(255)	NULL,
	`birth`	DATE	NULL,
	`preference`	INT	NULL,
	`score`	INT	NULL,
	`joinDate`	DATETIME NULL,
	`userID`	VARCHAR(50) NOT NULL,
	`gender`	CHAR(1)	NULL
);

CREATE TABLE `Portfolio` (
	`portfolioID`	INT	NOT NULL,
	`creationDate`	DATETIME	NULL,
	`total`	DECIMAL(15,2)	NULL,
	`expectedReturn`	DECIMAL(5,2)	NULL,
	`riskLevel`	INT	NULL,
	`portfolioName`	VARCHAR(100)	NULL,
	`userNum`	INT	NOT NULL
);


CREATE TABLE `SavingsProduct` (
	`productID`	INT	NOT NULL,
	`riskLevel`	INT	NULL,
	`kor_co_nm`	VARCHAR(100)	NULL,
	`fin_prdt_cd`	VARCHAR(50)	NULL,
	`fin_prdt_nm`	VARCHAR(100)	NULL,
	`mtrt_int`	DECIMAL(15,2)	NULL,
	`spcl_cnd`	TEXT	NULL,
	`join_deny`	TEXT	NULL,
	`join_member`	TEXT	NULL,
	`max_limit`	VARCHAR(255)	NULL,
	`dcls_strt_day`	VARCHAR(255)	NULL,
	`dcls_end_day`	VARCHAR(255)	NULL,
	`intr_rate_type`	VARCHAR(50)	NULL,
	`intr_rate_type_nm`	VARCHAR(50)	NULL,
	`save_trm`	VARCHAR(10)	NULL,
	`intr_rate`	DECIMAL(5,2)	NULL,
	`intr_rate2`	DECIMAL(5,2)	NULL,
	`join_way`	TEXT	NULL,
	`etc_note`	TEXT	NULL,
	`rsrv_type`	VARCHAR(50)	NULL,
	`rsrv_type_nm`	VARCHAR(50)	NULL,
	`hit`	INT	NULL
);


CREATE TABLE `BondProduct` (
	`productID`	INT	NOT NULL,
	`product_cd`	VARCHAR(12)	NULL,
	`issuco_custno`	DECIMAL(10)	NULL,
	`secn_nm`	VARCHAR(100)	NULL,
	`secn_kacd`	CHAR(4)	NULL,
	`issu_dt`	VARCHAR(8)	NULL,
	`xpir_dt`	VARCHAR(8)	NULL,
	`issu_cur_cd`	CHAR(3)	NULL,
	`first_issu_amt`	DECIMAL(20,4)	NULL,
	`issu_rema`	DECIMAL(18,2)	NULL,
	`payin_amt`	DECIMAL(18,2)	NULL,
	`couponRate`	DECIMAL(13,10)	NULL,
	`expiredRate`	DECIMAL(14,10)	NULL,
	`recu_whcd`	CHAR(2)	NULL,
	`issu_whcd`	CHAR(1)	NULL,
	`particul_bond_kind_tpcd`	CHAR(1)	NULL,
	`option_tpcd`	CHAR(4)	NULL,
	`forc_erly_red_yn`	CHAR(1)	NULL,
	`mr_chg_tpcd`	CHAR(1)	NULL,
	`regi_org_tpcd`	CHAR(2)	NULL,
	`grty_tpcd`	CHAR(1)	NULL,
	`signa_tpcd`	CHAR(1)	NULL,
	`rank_tpcd`	CHAR(1)	NULL,
	`int_pay_way_tpcd`	CHAR(1)	NULL,
	`sint_cint_tpcd`	CHAR(1)	NULL,
	`irate_chg_tpcd`	CHAR(1)	NULL,
	`xpir_guar_prate`	DECIMAL(13,10)	NULL,
	`xpir_guar_prate_tpcd`	CHAR(2)	NULL,
	`prcp_red_whcd`	CHAR(2)	NULL,
	`applyDate`	VARCHAR(8)	NULL,
	`deListingDate`	VARCHAR(8)	NULL,
	`kis_valat_grd_cd`	CHAR(3)	NULL,
	`nice_valat_grd_cd`	CHAR(3)	NULL,
	`sci_valat_grd_cd`	CHAR(3)	NULL,
	`kr_valat_grd_cd`	CHAR(3)	NULL,
	`eltsc_yn`	CHAR(1)	NULL,
	`exer_mbody_tpcd`	CHAR(1)	NULL,
	`int_Estm_mann_tpcd`	CHAR(1)	NULL,
	`hit`	INT	NULL
);

CREATE TABLE `Stock` (
	`stockCode`	VARCHAR(10)	NOT NULL,
	`stcokName`	VARCHAR(10)	NULL,
	`dailyPrice`	DECIMAL(10,2)	NULL,
	`field`	INT	NULL,
	`itmsNm`	VARCHAR(50)	NULL,
	`mrktCtg`	VARCHAR(50)	NULL,
	`clpr`	INT	NULL,
	`vs`	INT	NULL,
	`fltRt`	DECIMAL(5,2)	NULL,
	`mkp`	INT	NULL,
	`hipr`	INT	NULL,
	`lopr`	INT	NULL,
	`trqu`	BIGINT	NULL,
	`trPrc`	BIGINT	NULL,
	`istgStCnt`	BIGINT	NULL,
	`mrktTotAmt`	BIGINT	NULL
);

CREATE TABLE `CartItem` (
	`cartID`	INT	NOT NULL,
	`productID`	INT	NOT NULL,
	`userNum`	INT	NOT NULL
);

CREATE TABLE `Insight` (
	`insightID`	INT	NOT NULL,
	`type`	VARCHAR(20)	NULL,
	`content`	TEXT	NULL,
	`URL`	VARCHAR(255)	NULL
);

CREATE TABLE `Product` (
	`productID`	INT	NOT NULL,
	`productType`	CHAR(1)	NULL
);

CREATE TABLE `PortfolioItem` (
	`portfolioItemID`	INT	NOT NULL,
	`portfolioID`	INT	NOT NULL,
	`productID`	INT	NOT NULL,
	`stockCode`	VARCHAR(10)	NOT NULL,
	`amount`	INT	NULL,
	`expectedReturn`	DECIMAL(5,2)	NULL
);

CREATE TABLE `FundProduct` (
	`ProductID`	INT	NOT NULL,
	`Company_name`	VARCHAR(100)	NULL,
	`ProductName`	VARCHAR(255)	NULL,
	`1_yield`	DECIMAL(15,2)	NULL,
	`3_yield`	DECIMAL(15,2)	NULL,
	`6_yield`	DECIMAL(15,2)	NULL,
	`12_yield`	DECIMAL(15,2)	NULL,
	`RiskLevel`	INT	NULL,
	`FundType`	INT	NULL,
	`Advanced_Fee`	DECIMAL(15,2)	NULL,
	`Total_payoff_rate`	DECIMAL(15,2)	NULL,
	`hit`	INT	NULL
);

CREATE TABLE `AgeGroupProductHits` (
	`SelectedNum`	INT	NULL,
	`AgeGroup`	INT	NULL,
	`HitCount`	INT 	NULL,
	`ProductID`	INT	NOT NULL
);

ALTER TABLE `User` ADD CONSTRAINT `PK_USER` PRIMARY KEY (
	`UserNum`
);

ALTER TABLE `Portfolio` ADD CONSTRAINT `PK_PORTFOLIO` PRIMARY KEY (
	`PortfolioID`
);

ALTER TABLE `SavingsProduct` ADD CONSTRAINT `PK_SAVINGSPRODUCT` PRIMARY KEY (
	`ProductID`
);

ALTER TABLE `BondProduct` ADD CONSTRAINT `PK_BONDPRODUCT` PRIMARY KEY (
	`ProductID`
);

ALTER TABLE `Stock` ADD CONSTRAINT `PK_STOCK` PRIMARY KEY (
	`StockCode`
);

ALTER TABLE `CartItem` ADD CONSTRAINT `PK_CARTITEM` PRIMARY KEY (
	`CartID`
);

ALTER TABLE `Insight` ADD CONSTRAINT `PK_INSIGHT` PRIMARY KEY (
	`InsightID`
);

ALTER TABLE `Product` ADD CONSTRAINT `PK_PRODUCT` PRIMARY KEY (
	`ProductID`
);

ALTER TABLE portfolioitem ADD CONSTRAINT `PK_PORTFOLIOITEM` PRIMARY KEY (
	portfolioItemID
);

ALTER TABLE `FundProduct` ADD CONSTRAINT `PK_FUNDPRODUCT` PRIMARY KEY (
	`ProductID`
);

ALTER TABLE `AgeGroupProductHits` ADD CONSTRAINT `PK_AGEGROUPPRODUCTHITS` PRIMARY KEY (
	`SelectedNum`
);

ALTER TABLE `SavingsProduct` ADD CONSTRAINT `FK_Product_TO_SavingsProduct_1` FOREIGN KEY (
	`ProductID`
)
REFERENCES `Product` (
	`ProductID`
);

ALTER TABLE `BondProduct` ADD CONSTRAINT `FK_Product_TO_BondProduct_1` FOREIGN KEY (
	`ProductID`
)
REFERENCES `Product` (
	`ProductID`
);

ALTER TABLE `FundProduct` ADD CONSTRAINT `FK_Product_TO_FundProduct_1` FOREIGN KEY (
	`ProductID`
)
REFERENCES `Product` (
	`ProductID`
);


CREATE DATABASE CROSS_FIT;
USE CROSS_FIT;

CREATE TABLE PRODUCT (
                         productID INT auto_increment primary KEY,
                         productType CHAR(1) NOT NULL
);

CREATE TABLE SavingsProduct(
                               productID INT PRIMARY KEY,
                               fin_co_no VARCHAR(50),
                               fin_prdt_cd VARCHAR(50),
                               kor_co_nm VARCHAR(100),
                               fin_prdt_nm VARCHAR(100),
                               dcls_month VARCHAR(6),
                               join_way TEXT,
                               mtrt_int TEXT,
                               spcl_cnd TEXT,
                               join_deny INT,
                               join_member VARCHAR(100),
                               etc_note TEXT,
                               max_limit VARCHAR(255),
                               dcls_strt_day VARCHAR(8),
                               dcls_end_day VARCHAR(8),
                               RiskLevel INT,
                               hit INT DEFAULT 0,
                               FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

CREATE TABLE SavingProductRates (
                                    productID INT,
                                    save_trm INT,
                                    intr_rate_type CHAR(1),
                                    intr_rate_type_nm VARCHAR(20),
                                    intr_rate DECIMAL(5,2),
                                    intr_rate2 DECIMAL(5,2),
                                    rsrv_type CHAR(1),
                                    rsrv_type_nm VARCHAR(50),
                                    PRIMARY KEY (productID, save_trm),
                                    FOREIGN KEY (productID) REFERENCES SavingsProduct(productID)
);

select * from product;
select * from savingsProduct;
select * from savingproductrates;

DELETE FROM product;  -- 테이블에서 모든 데이터를 삭제
ALTER TABLE product AUTO_INCREMENT = 1;

DELETE FROM SAVINGSPRODUCT;
ALTER TABLE SAVINGSPRODUCT AUTO_INCREMENT = 1;

DELETE FROM SAVINGPRODUCTRATES;
ALTER TABLE SAVINGPRODUCTRATES auto_increment = 1;

# start here

DROP DATABASE CROSS_FIT;

CREATE DATABASE CROSS_FIT;

USE CROSS_FIT;

CREATE TABLE PRODUCT (
                         productID INT auto_increment primary KEY,
                         productType CHAR(1) NOT NULL
);

CREATE TABLE SavingsProduct(
                               productID INT PRIMARY KEY,
                               fin_co_no VARCHAR(50),
                               fin_prdt_cd VARCHAR(50),
                               kor_co_nm VARCHAR(100),
                               fin_prdt_nm VARCHAR(100),
                               dcls_month VARCHAR(6),
                               join_way TEXT,
                               mtrt_int TEXT,
                               spcl_cnd TEXT,
                               join_deny INT,
                               join_member VARCHAR(100),
                               etc_note TEXT,
                               max_limit VARCHAR(255),
                               dcls_strt_day VARCHAR(8),
                               dcls_end_day VARCHAR(8),
                               RiskLevel INT,
                               hit INT DEFAULT 0,
                               FOREIGN KEY (ProductID) REFERENCES Product(ProductID)
);

CREATE TABLE SavingProductRates (
                                    productID INT,
                                    save_trm INT,
                                    intr_rate_type CHAR(1),
                                    intr_rate_type_nm VARCHAR(20),
                                    intr_rate DECIMAL(5,2),
                                    intr_rate2 DECIMAL(5,2),
                                    rsrv_type VARCHAR(10),
                                    rsrv_type_nm VARCHAR(50),
                                    PRIMARY KEY (productID, save_trm, rsrv_type),
                                    FOREIGN KEY (productID) REFERENCES SavingsProduct(productID)
);

CREATE TABLE FundProduct (
                             productID INT NOT NULL,               -- 금융상품 테이블의 외래키
                             company_nm VARCHAR(100),            -- 운용사명
                             product_nm VARCHAR(255),             -- 상품명
                             yield_1 DECIMAL(15,2),                -- 1개월 누적수익률
                             yield_3 DECIMAL(15,2),                -- 3개월 누적수익률
                             yield_6 DECIMAL(15,2),                -- 6개월 누적수익률
                             yield_12 DECIMAL(15,2),               -- 12개월 누적수익률
                             RiskLevel INT,                        -- 펀드 등급
                             fund_type VARCHAR(50),                         -- 펀드 유형
                             advanced_fee DECIMAL(15,2),           -- 선취수수료
                             total_payoff_rate DECIMAL(15,2),      -- 총 보수율
                             hit INT,                              -- 조회수
                             PRIMARY KEY (ProductID),              -- Primary Key 설정
                             FOREIGN KEY (ProductID) REFERENCES Product(ProductID) ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE BondProduct (
                             productID INT NOT NULL,                -- 상품ID (Product 테이블의 외래키)
                             basDt VARCHAR(8),                     -- 기준일자
                             crno VARCHAR(13),                      -- 법인등록번호
                             scrsItmsKcd VARCHAR(4),               -- 유가증권종목코드
                             isinCd VARCHAR(12),                    -- ISIN코드
                             scrsItmsKcdNm VARCHAR(50),             -- 유가증권종목코드명
                             bondIsurNm VARCHAR(100),               -- 채권발행인명
                             isinCdNm VARCHAR(100),                 -- ISIN코드명
                             bondIssuDt VARCHAR(8),                -- 채권발행일자
                             bondIssuAmt BIGINT,                    -- 채권발행금액
                             bondIssuCurCd VARCHAR(3),              -- 채권발행통화코드
                             bondIssuCurCdNm VARCHAR(50),           -- 채권발행통화코드명
                             bondExprDt VARCHAR(8),                -- 채권만기일자
                             bondPymtAmt BIGINT,                    -- 채권납입금액
                             irtChngDcd CHAR(1),                    -- 금리변동구분코드
                             irtChngDcdNm VARCHAR(50),              -- 금리변동구분코드명
                             bondSrfcInrt DECIMAL(5, 2),           -- 채권금리
                             bondIntTcd CHAR(1),                    -- 채권이자형구분코드
                             bondIntTcdNm VARCHAR(50),              -- 채권이자형구분코드명
                             intPayCyclCtt VARCHAR(50),             -- 이자지급주기내용
                             nxtmCopnDt VARCHAR(8),                -- 차기표일자
                             rbVopnDt VARCHAR(8),                  -- 직전이표일자
                             kbpScrsItmsKcdNm VARCHAR(100),         -- 한국신용평가유가증권종목종류코드명
                             niceScrsItmsKcdNm VARCHAR(100),        -- NICE평가정보유가증권종목종류코드명
                             fnScrsItmsKcdNm VARCHAR(100),          -- FN유가증권종목종류코드명
                             riskLevel INT,                         -- 위험도
                             hit INT DEFAULT 0,                     -- 조회수, 기본값 0
                             PRIMARY KEY (productID),               -- Primary Key 설정
                             FOREIGN KEY (productID) REFERENCES Product(productID) ON DELETE CASCADE ON UPDATE CASCADE
);

select * from product LIMIT 2000;
select * from savingsProduct;
select * from savingproductrates;
select * from fundproduct;
select * from bondproduct;


-- 실패시 밑에서 부터 테이블 삭제하고 다시 시작하셔야합니다!
DELETE FROM product;  -- 테이블에서 모든 데이터를 삭제
ALTER TABLE product AUTO_INCREMENT = 1;

DELETE FROM SAVINGSPRODUCT;
ALTER TABLE SAVINGSPRODUCT AUTO_INCREMENT = 1;

DELETE FROM SAVINGPRODUCTRATES;
ALTER TABLE SAVINGPRODUCTRATES auto_increment = 1;


CREATE TABLE `Portfolio` (
                             `portfolioID` INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                             `portfolioName`	VARCHAR(100)	NULL,
                             `creationDate`	DATETIME	NULL,
                             `total`	DECIMAL(15,2)	NULL,
                             `expectedReturn`	DECIMAL(5,2)	NULL,
                             `riskLevel`	INT	NULL,
                             `userNum`	INT	NOT NULL
);

CREATE TABLE `PortfolioItem` (
                                 `portfolioItemID`	INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
                                 `portfolioID`	INT	NOT NULL,
                                 `productID`	INT	    NULL,
                                 `stockCode`	VARCHAR(10)	    NULL,
                                 `amount`	INT	NULL,
                                 `expectedReturn`	DECIMAL(5,2)	NULL
);

CREATE TABLE `CartItem` (
                            `cartID`	INT	NOT NULL AUTO_INCREMENT PRIMARY KEY,
                            `productID`	INT	NOT NULL,
                            `memberNum`	BIGINT UNSIGNED	NOT NULL,
                            `productType` CHAR(1) NOT NULL,
                            `provider` VARCHAR(100) NOT NULL,
                            `productName` VARCHAR(100) NOT NULL,
                            `expectedReturn` DECIMAL(5,2) NULL,
                            `interestRate` DECIMAL(5,2) NULL
);

CREATE INDEX idx_portfolio_id ON portfolio(portfolioID);

ALTER TABLE `portfolioitem`
    ADD CONSTRAINT `FK_Portfolio_TO_PortfolioItem_1`
        FOREIGN KEY (`portfolioID`)
            REFERENCES `portfolio` (`portfolioID`)
            ON DELETE CASCADE;

use cross_fit;
CREATE TABLE `stock` (
                         `stockCode` VARCHAR(10) NOT NULL,          -- 주식 코드
                         `stockName` VARCHAR(50) NULL,              -- 주식 이름
                         `dailyPrice` DECIMAL(10,2) NULL,           -- 일일 가격
                         `field` INT NULL,                          -- 분야 코드
                         `itmsNm` VARCHAR(50) NULL,                 -- 항목 이름
                         `mrktCtg` VARCHAR(50) NULL,                -- 시장 카테고리
                         `clpr` INT NULL,                           -- 종가
                         `vs` INT NULL,                             -- 등락폭
                         `fltRt` DECIMAL(5,2) NULL,                 -- 등락률
                         `mkp` INT NULL,                            -- 시가
                         `hipr` INT NULL,                           -- 최고가
                         `lopr` INT NULL,                           -- 최저가
                         `trqu` BIGINT NULL,                        -- 거래량
                         `trPrc` BIGINT NULL,                       -- 거래대금
                         `istgStCnt` BIGINT NULL,                   -- 상장 주식 수
                         `mrktTotAmt` BIGINT NULL,                  -- 시장 총액
                         PRIMARY KEY (`stockCode`)                  -- stockCode를 기본 키로 설정
);

CREATE TABLE `Member` (
                          `member_num`	BIGINT UNSIGNED	NOT NULL auto_increment primary key,
                          `member_id` varchar(100) NOT NULL unique,
                          `member_name`	VARCHAR(50)	NOT NULL,
                          `email` VARCHAR(100) NOT NULL unique,
                          `password`	VARCHAR(255) NOT NULL,
                          `birth`	DATE	NULL,
                          `preference`	INT	NULL,
                          `invest_score`	INT	NULL,
                          `reg_Date`	DATETIME NULL,
                          `gender`	VARCHAR(10)	NULL
);