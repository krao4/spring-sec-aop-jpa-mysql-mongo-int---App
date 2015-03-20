# spring-sec-aop-jpa-mysql-mongo-int---App
spring-sec-aop-jpa-mysql-mongo-int - App



MySQL SQL Script:
--create schema pocdb

DROP TABLE IF EXISTS policy;

CREATE TABLE pocdb.policy (
       ID INT NOT NULL AUTO_INCREMENT
     , POLICY_NAME VARCHAR(60) NOT NULL
     , START_DATE VARCHAR(11)
     , DESCRIPTION VARCHAR(2000)
     , VERSION INT NOT NULL DEFAULT 0
     , PRIMARY KEY (ID)
);

insert into policy (policy_name, start_date, description, version) values ('Life', '1984-07-30', 'Life insurance policy', 1);
insert into policy (policy_name, start_date, description, version) values ('Annuity', '2012-07-30', 'Annuity policy', 1);
insert into policy (policy_name, start_date, description, version) values ('Retirement', '2010-07-30', 'Retirement policy', 1);
commit;

select * from policy;

CREATE TABLE policy_history (
       ID INT NOT NULL AUTO_INCREMENT,
       POLICY_ID INT NOT NULL REFERENCES policy(id), 
      DESCRIPTION VARCHAR(2000),
      VERSION INT NOT NULL DEFAULT 0,
     PRIMARY KEY (ID)
);




Mongo db scripts:

--create db demodb1

1. 

db.customer.insert({"_class" : "com.sapient.springapp.domain.Customer", "firstName" : "Alice", "lastName" : "Smith" })
db.customer.insert({"_class" : "com.sapient.springapp.domain.Customer", "firstName" : "Bob", "lastName" : "Smith" })
db.customer.insert({"_class" : "com.sapient.springapp.domain.Customer", "firstName" : "Rob", "lastName" : "Dough" })
db.customer.insert({"_class" : "com.sapient.springapp.domain.Customer", "firstName" : "Qwerty", "lastName" : "Gob" })
db.customer.insert({"_class" : "com.sapient.springapp.domain.Customer", "firstName" : "Jane", "lastName" : "Watson" })




2. 
To insert mainnav data, do a POST on the following service

POST http://localhost:8080/VOYAAPP/mainnav/

Request Headers:

Accept: application/json
SM_USER: admin1


Request Body:


{
  sections: [
    {
      "label":"PLANNING &amp; ADVICE","href":"#","sub-links":[{"label":"Getting Started with Personal Finance","href":"#","sub-links":[{"label":"Getting Started","href":"#"},{"label":"Saving &amp; Budgeting","href":"#"},{"label":"Setting a Goal","href":"#"},{"label":"Creating &amp; Managing a Plan","href":"#"},{"label":"Maximizing Your Employer Plan","href":"#"},{"label":"Investing Basics","href":"#"}]},{"label":"Planning by Age","href":"#","sub-links":[{"label":"Planning in your 20s","href":"#"},{"label":"Planning in your 30s","href":"#"},{"label":"Planning in your 40s","href":"#"},{"label":"Planning in your 50s","href":"#"},{"label":"Planning in your 60s","href":"#"},{"label":"Planning in your 70s","href":"#"}]},{"label":"Saving for Retirement","href":"#","sub-links":[{"label":"Planning","href":"#"},{"label":"Expenses to Consider","href":"#"}]},{"label":"Nearing Retirement","href":"#","sub-links":[{"label":"Nearing Retirement","href":"#"},{"label":"Planning Your Transition","href":"#"}]},{"label":"In Retirement","href":"#","sub-links":[{"label":"Sources of Income","href":"#"},{"label":"Expenses to Consider","href":"#"},{"label":"Medical &amp; Health","href":"#"},{"label":"Leaving a Legacy","href":"#"}]},{"label":"Work","href":"#"},{"label":"Family","href":"#","sub-links":[{"label":"Family","href":"#"},{"label":"Marriage","href":"#"},{"label":"Children","href":"#"},{"label":"Aging","href":"#"},{"label":"Leaving a Legacy","href":"#"}]},{"label":"Major &amp; Unexpected Expenses","href":"#"}]},{"label":"PRODUCTS &amp; SERVICES","href":"#","sub-links":[{"label":"IRAs","href":"#","sub-links":[{"label":"Rollover IRA","href":"#"},{"label":"Traditional IRA","href":"#"},{"label":"Roth IRA","href":"#"},{"label":"IRA for Spouses","href":"#"},{"label":"SEP IRA","href":"#"},{"label":"Simple IRA","href":"#"}]},{"label":"Retirement Plans","href":"#","sub-links":[{"label":"401k","href":"#"},{"label":"403b","href":"#"},{"label":"457","href":"#"},{"label":"SEP IRA","href":"#"},{"label":"Simple IRA","href":"#"},{"label":"401a","href":"#"}]},{"label":"Employee Benefits at Work","href":"#","sub-links":[{"label":"Life Insurance at Work","href":"#"},{"label":"Accident Insurance at Work","href":"#"},{"label":"Critical Illness Insurance at Work","href":"#"},{"label":"Disability Income Insurance at Work","href":"#"},{"label":"Hospital Confinement Indemnity Insurance at Work","href":"#"}]},{"label":"Life Insurance","href":"#","sub-links":[{"label":"Term Life Insurance","href":"#"},{"label":"Universal Life Insurance","href":"#"},{"label":"Indexed Universal Life Insurance","href":"#"},{"label":"Variable Universal Life Insurance","href":"#"},{"label":"Survivorship Life Insurance","href":"#"}]},{"label":"Annuities","href":"#","sub-links":[{"label":"Fixed Annuities","href":"#"},{"label":"Indexed Annuities","href":"#"},{"label":"Income Annuities","href":"#"},{"label":"Living Benefits","href":"#"}]},{"label":"College Savings","href":"#","sub-links":[{"label":"Unified Gift/Trust to Minors","href":"#"},{"label":"Education Savings Account","href":"#"},{"label":"529 Plans","href":"#"}]},{"label":"Mutual Funds","href":"#"},{"label":"Financial Advisor","href":"#"},{"label":"HSA","href":"#"}]},{"label":"TOOLS","href":"#","sub-links":[{"label":"Orange Money","href":"#"},{"label":"Calculators","href":"#","sub-links":[{"label":"Monthly Budget Worksheet","href":"#"},{"label":"College Savings Calculator","href":"#"},{"label":"Retirement &amp; Paycheck Analysis","href":"#"},{"label":"IRA: Roth vs. Traditional Calculator","href":"#"},{"label":"401(k): Roth vs. Traditional Calculator","href":"#"},{"label":"IRA: Roth Conversion Calculator","href":"#"},{"label":"Get a Life Insurance Quote","href":"#"},{"label":"Calculate Your Life Insurance Needs","href":"#"}]},{"label":"Forms","href":"#"}]},{"label":"CONTACT US","href":"#"}
]}

