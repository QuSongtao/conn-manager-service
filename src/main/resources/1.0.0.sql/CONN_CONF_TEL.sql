﻿SET DEFINE OFF;
Insert into CONN_CONF_TEL
   (ID, TELID, TELNAME, TRANSFERTYPE, QUEUENAME, 
    REMOTESYSTEM)
 Values
   ('027c0e7b-a004-43d6-ab01-8e01291da7d3', 'CRERP00001', '生产物料信息上传', 'S', 'WIN.Q1', 
    '产销ERP系统');
Insert into CONN_CONF_TEL
   (ID, TELID, TELNAME, TRANSFERTYPE, QUEUENAME, 
    REMOTESYSTEM)
 Values
   ('a71f1151-f3a9-4369-a575-18cc5512490b', 'ERPCR00001', '生产实绩信息回传', 'S', 'L3.CR.Q.1', 
    '产销ERP');
COMMIT;