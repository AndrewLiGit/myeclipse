�����Լ�����������������

1.���  ��Ҫ���update������ һ������A������һ������B���޸Ĺ����ǻ�û���ύ������

2.�����ظ���  ��Ҫ���update������ һ������A�ڵ�һ�ζ����ݺ͵ڶ��ζ�����֮��,����һ������B��������ݸ��Ĳ��ύ��,���Ծͳ���������A�����һ����������,���Ƕ����Ľ���ǲ�ͬ�ġ�

3.�ö�  ��Ҫ��Ե���insert/delete����������A��һ����where����ɸѡ����10������,����A�ڶ�����ͨ����where����ɸѡ����ȴ��11������,��Ϊ����B������A�ĵ�һ�κ͵ڶ��β�ѯֱ�ӽ����˲������,���Ҳ�������������������A��whereɸѡ����.

isolation  ������뼶��

 read-uncommitted  ���ύҲ�ܶ�
 read-committed    �ύ֮����ܶ� ��������
 repeatable-read   ���������Ͳ����ظ���
 serializable      �������ⶼ�����

 ����Խ�߽��������Խ�൫��Ч��Խ�͡�
 ע��:�������������ݿⶼ֧��������������뼶��,����oracle��ֻ֧�ֵڶ��ֺ͵�����������,����mysql������ȫ֧��.
 
 oracle����Ĭ�ϵ�������뼶���ǵڶ���:read-committed
 
 oralce��������������뼶��:
 Set Transaction Isolation Level Read Uncommitted
 Set Transaction Isolation Level Read Committed
 Set Transaction Isolation Level Read Repeatable
 Set Transaction Isolation Level Serializable

 hibernate.cfg.xml��Ҳ��������������뼶��:
 <property name="connection.isolation">2</property>

 read-uncommitted    1
 read-committed      2
 repeatable-read     4
 serializable        8
 
 hibernate�о���������������뼶��Ҳ��Ҫ�����ݿ����Ƿ�֧�ֵ�,oracle���ݿ�ֻ֧��read-committed��serializable.

hibernate�е�������:

�����hibernate�а�������뼶������Ϊserializable,��ô���Ǿ���ȫû�б�Ҫʹ��hibernate�е���������,�����������ú������ִ��Ч�ʺܵ�,��������һ�������Ϊread-committed(һ��Ĭ��Ҳ�����),�������ܽ���������һ����Ч��,ֻ����������������Ȼ���в����ظ���������(�ö�������,���ٳ���),����������hibernate�Ϳ���ʹ��������������������.

hibernate�������Ʒ�Ϊ�ֹ����ͱ�����

������:hibernate�����������ݿ����������������ɵ�,����oracle��sql��������for update��for update nowait��ʵ�ֵġ�
select * from teacher where id=1 for update
select * from teacher where id=1 for update nowait

�ֹ���:����hibernate�е�һЩ���ú���������ɵ�.

//�鿴���ݿ����Ǹ�session�м�����
select sess.sid,sess.serial#
from v$locked_object lo, dba_objects ao, v$session sess
where ao.object_id = lo.object_id
and lo.session_id = sess.sid;

//�����sessionɱ�� ǿ�ƽ���
alter system kill session 'sid,serial#'; 