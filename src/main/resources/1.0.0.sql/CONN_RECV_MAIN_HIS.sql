﻿SET DEFINE OFF;
Insert into CONN_RECV_MAIN_HIS
   (ID, TELID, TELTYPE, MSGID, RECVTIME, 
    DEALFLAG, DEALTIME, DES, ERRCODE)
 Values
   ('027c0e7b-a004-43d6-ab01-8e01291da7d3', 'dddd', 'SK', '027c0e7b-a004-43d6-ab01-8e01291da7d3', TO_DATE('01/10/2019 00:33:40', 'MM/DD/YYYY HH24:MI:SS'), 
    '0', TO_DATE('01/10/2019 00:40:00', 'MM/DD/YYYY HH24:MI:SS'), 'success!', '0000');
COMMIT;
