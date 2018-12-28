DROP TABLE IF EXISTS versions;
DROP TABLE IF EXISTS releases;
DROP TABLE IF EXISTS projects;
DROP TABLE IF EXISTS categories;
DROP TABLE IF EXISTS stages;

DROP TABLE IF EXISTS installgroups;
DROP TABLE IF EXISTS labs;
DROP TABLE IF EXISTS clouds;


CREATE TABLE stages (
  stage       TEXT    NOT NULL PRIMARY KEY
);
INSERT INTO stages VALUES ('dev');
INSERT INTO stages VALUES ('test');
INSERT INTO stages VALUES ('prod');


CREATE TABLE categories (
  category    INTEGER NOT NULL PRIMARY KEY,
  description TEXT    NOT NULL
);
INSERT INTO categories VALUES (0, 'Hidden');
INSERT INTO categories VALUES (1, 'PostgreSQL');
INSERT INTO categories VALUES (2, 'Extensions');
INSERT INTO categories VALUES (3, 'Servers');
INSERT INTO categories VALUES (4, 'Applications');
INSERT INTO categories VALUES (5, 'Connectors');
INSERT INTO categories VALUES (6, 'Frameworks');


CREATE TABLE projects (
  project   	 TEXT    NOT NULL PRIMARY KEY,
  category  	 INTEGER NOT NULL,
  port      	 INTEGER NOT NULL,
  depends   	 TEXT    NOT NULL,
  start_order    INTEGER NOT NULL,
  homepage_url   TEXT    NOT NULL,
  short_desc     TEXT    NOT NULL,
  FOREIGN KEY (category) REFERENCES categories(category)
);
INSERT INTO projects VALUES ('hub', 0, 0, 'hub', 0, 'http://bigsql.org', 'Pretty Good CLI');

INSERT INTO projects VALUES ('pg', 1, 5432, 'hub', 1, 'http://postgresql.org', 'Advanced RDBMS');

INSERT INTO projects VALUES ('maria', 3, 1234, 'hub', 1, 'http://mariadb.org', '');

INSERT INTO projects VALUES ('plprofiler', 2, 0, 'perl', 0, 'https://bitbucket.org/openscg/plprofiler', 'PLpg/SQL Profiler');
INSERT INTO projects VALUES ('postgis', 2, 0, 'hub', 0, 'http://postgis.net', 'Spatial Database Extender for PG');
INSERT INTO projects VALUES ('cassandra_fdw', 2, 0, 'hub', 0, 'http://cassandrafdw.org', 'Cassandra Foreign Data Wrapper');
INSERT INTO projects VALUES ('hadoop_fdw', 2, 0, 'hub', 0, 'http://hadoopfdw.org', 'Hadoop Foreign Data Wrapper');
INSERT INTO projects VALUES ('oracle_fdw', 2, 0, 'hub', 0, 'https://github.com/laurenz/oracle_fdw', 'Oracle Foreign Data Wrapper');
INSERT INTO projects VALUES ('mysql_fdw', 2, 0, 'hub', 0, 'https://github.com/EnterpriseDB/mysql_fdw', 'MySQL Foreign Data Wrapper');
INSERT INTO projects VALUES ('tds_fdw', 2, 0, 'hub', 0, 'https://github.com/tds-fdw/tds_fdw', 'TDS (Sybase and MS SQL) Foreign Data Wrapper');
INSERT INTO projects VALUES ('orafce', 2, 0, 'hub', 0, 'https://github.com/orafce/orafce', 'Oracle Compatible Functions');
INSERT INTO projects VALUES ('pljava', 2, 0, 'hub', 0, 'https://github.com/tada/pljava', 'Java Procedural Language');
INSERT INTO projects VALUES ('timescaledb', 2, 0, 'hub', 0, 'https://github.io', 'Timeseries Extension for PG');
INSERT INTO projects VALUES ('hintplan', 2, 0, 'hub', 0, 'https://osdn.net/projects/pghintplan', 'Guiding the planner with hints');
INSERT INTO projects VALUES ('bulkload', 2, 0, 'hub', 0, 'http://ossc-db.github.io/pg_bulkload/pg_bulkload.html', 'High Speed Data Loader');
INSERT INTO projects VALUES ('repack', 2, 0, 'hub', 0, 'http://github.com/reorg/pg_repack', 'Online Table Compaction');
INSERT INTO projects VALUES ('logical', 2, 0, 'hub', 0, 'https://github.com/2ndQuadrant/pglogical', 'Logical Replication');
INSERT INTO projects VALUES ('partman', 2, 0, 'hub', 0, 'https://github.com/keithf4/pg_partman', 'Table Partition Manager');
INSERT INTO projects VALUES ('pgaudit', 2, 0, 'hub', 0, 'http://pgaudit.org',                  'Auditing Extension');
INSERT INTO projects VALUES ('setuser', 2, 0, 'hub', 0, 'https://github.com/pgaudit/set_user', 'Secure Privilege Escalation');
INSERT INTO projects VALUES ('pldebugger', 2, 0, 'hub', 0, 'https://bigsql.org/docs/debugger/', 'Procedural Language Debugger');
INSERT INTO projects VALUES ('background', 2, 0, 'hub', 0, 'https://github.com/vibhorkum/pg_background', 'Autonomous SQL Txns');

INSERT INTO projects VALUES ('hadoop', 3, 50010, 'hub', 7, 'http://hadoop.apache.org', 'Distributed Framework');
INSERT INTO projects VALUES ('zookeeper', 3, 2181, 'hub', 6, 'http://zookeeper.apache.org', 'Cluster Coordinator');
INSERT INTO projects VALUES ('cassandra', 3, 7000, 'hub', 4, 'http://cassandra.apache.org', 'Multi-master/Multi-datacenter');
INSERT INTO projects VALUES ('spark', 3, 7077, 'hub', 5, 'http://spark.apache.org', 'Speedy Distributed Data Access');
INSERT INTO projects VALUES ('hive', 3, 10000, 'hub', 8, 'http://hive.apache.org', 'Hadoop SQL Queries');
INSERT INTO projects VALUES ('presto', 3, 8080, 'hub', 8, 'http://prestodb.io', 'SQL Query Engine for Big Data');
INSERT INTO projects VALUES ('sqoop', 4, 0, 'hub', 0, 'http://sqoop.apache.org', 'Bulk Data Transfers');

INSERT INTO projects VALUES ('pgadmin', 4, 1, 'hub', 0, 'http://pgadmin.org', 'Desktop Development Environment');

INSERT INTO projects VALUES ('python', 6, 0, 'hub', 0, 'http://python.org', 'Python Programming Language');
INSERT INTO projects VALUES ('perl', 6, 0, 'hub', 0, 'http://strawberryperl.com', 'Perl Programming Language');
INSERT INTO projects VALUES ('tcl', 6, 0, 'hub', 0, 'http://tcl.tk', 'Tcl Programming Language');

INSERT INTO projects VALUES ('collectd', 3, 1, 'hub', 1, 'http://collectd.org', 'Metrics Collection');
INSERT INTO projects VALUES ('grafana', 3, 3000, 'hub', 1, 'https://github.com/grafana/grafana', 'Monitoring & Metrics Dashboard');



CREATE TABLE releases (
  component  TEXT    NOT NULL PRIMARY KEY,
  project    TEXT    NOT NULL,
  disp_name  TEXT    NOT NULL,
  short_desc TEXT    NOT NULL,
  sup_plat   TEXT    NOT NULL,
  doc_url    TEXT    NOT NULL,
  stage      TEXT    NOT NULL,
  FOREIGN KEY (project) REFERENCES projects(project),
  FOREIGN KEY (stage)   REFERENCES stages(stage)
);
INSERT INTO releases VALUES ('hub',        'hub',      'Hidden', 'Hidden', '',        '', 'prod');

INSERT INTO releases VALUES ('pg94',       'pg',       'PostgreSQL 9.4', 'PG Server (bigsql)', '', 'http://www.postgresql.org/docs/9.4/', 'prod');
INSERT INTO releases VALUES ('pg95',       'pg',       'PostgreSQL 9.5', 'PG Server (bigsql)', '', 'http://www.postgresql.org/docs/9.5/', 'prod');
INSERT INTO releases VALUES ('pg96',       'pg',       'PostgreSQL 9.6', 'PG Server (bigsql)', '', 'http://www.postgresql.org/docs/9.6/', 'prod');
INSERT INTO releases VALUES ('pg10',       'pg',       'PostgreSQL 10',  'PG Server (bigsql)', '', 'http://www.postgresql.org/docs/10/', 'prod');
INSERT INTO releases VALUES ('pg11',       'pg',       'PostgreSQL 11',  'PG Server (bigsql)', '', 'http://www.postgresql.org/docs/11/', 'prod');

INSERT INTO releases VALUES ('pgdg10',     'pg',       'PostgreSQL 10',  'PG Server (pgdg)',   '', 'http://www.postgresql.org/docs/10/',  'prod');
INSERT INTO releases VALUES ('pgdg11',     'pg',       'PostgreSQL 11',  'PG Server (pgdg)',   '', 'http://www.postgresql.org/docs/11/',  'prod');
INSERT INTO releases VALUES ('pgadmin3',   'pgadmin',  'pgAdmin3',       'Fat Client Dev Tool', '', 'http://pgadmin3.org', 'prod');

INSERT INTO releases VALUES ('maria103',   'maria',    'MariaDB 10.3',   'MariaDB Server', '', 'http://www.mariadb.org/docs', 'test');


INSERT INTO releases VALUES ('cassandra31', 'cassandra','Cassandra 3.1',  'Cassandra Server',         'linux64', '', 'test');
INSERT INTO releases VALUES ('spark23',    'spark',    'Spark 2.3',      'Cluster Computing Server', 'linux64', '', 'test');
INSERT INTO releases VALUES ('hadoop28',   'hadoop',   'Hadoop 2.8',     'Distributed Data Storage', 'linux64', '', 'test');
INSERT INTO releases VALUES ('hive23',     'hive',     'Hive 2.3',       'Apache Hive Server',       'linux64', '', 'test');
INSERT INTO releases VALUES ('presto',     'presto',   'Presto',         'Presto DB Server',         'linux64', '', 'test');
INSERT INTO releases VALUES ('zookeeper34','zookeeper','ZooKeeper 3.4',  'Apache ZooKeeper',         'linux64', '', 'test');
INSERT INTO releases VALUES ('sqoop',      'sqoop',    'Sqoop 1.x',      'Apache Sqoop',             'linux64', '', 'test');

INSERT INTO releases VALUES ('python2',    'python',   'Python 2.7', 'Python Programming Language', '', '', 'prod');
INSERT INTO releases VALUES ('python37',   'python',   'Python 3.7', 'Python Programming Language', '', '', 'prod');

INSERT INTO releases VALUES ('perl5',      'perl',     'Perl 5', 'Perl Programming Language', '', '', 'prod');
INSERT INTO releases VALUES ('tcl86',      'tcl',      'Tcl', 'Tcl Programming Language', '', '', 'prod');

INSERT INTO releases VALUES ('bulkload3-pg10', 'bulkload', 'pgBulkload', 'Quick Data Loading', '', '', 'prod');

INSERT INTO releases VALUES ('logical2-pg96', 'logical', 'pgLogical', 'Logical Replication', '', '', 'prod');

INSERT INTO releases VALUES ('pgpartman3-pg10', 'partman', 'pgPartMan', 'Manage Partitioned Tables by Time or ID', '', '', 'prod');
INSERT INTO releases VALUES ('pgpartman4-pg10', 'partman', 'pgPartMan', 'Manage Partitioned Tables by Time or ID', '', '', 'prod');
INSERT INTO releases VALUES ('pgpartman4-pg11', 'partman', 'pgPartMan', 'Manage Partitioned Tables by Time or ID', '', '', 'prod');

INSERT INTO releases VALUES ('repack14-pg10', 'repack', 'pgRepack', 'Online Table Reorganization', '', '', 'prod');
INSERT INTO releases VALUES ('repack14-pg11', 'repack', 'pgRepack', 'Online Table Reorganization', '', '', 'prod');

INSERT INTO releases VALUES ('setuser1-pg10',  'setuser', 'SetUser', 'PostgreSQL extension allowing privilege escalation with enhanced logging and control', '', '', 'prod');

INSERT INTO releases VALUES ('pgaudit12-pg10', 'pgaudit', 'pgAudit', 'PostgreSQL Auditing Extension', '', 'http://pgaudit.org', 'prod');

INSERT INTO releases VALUES ('pldebugger96-pg10', 'pldebugger', 'plDebugger', 'Debug Stored Procedures', '', '', 'prod');

INSERT INTO releases VALUES ('background1-pg96', 'background', 'pgBackground', 'Autonomous SQL Txns', '', '', 'prod');

INSERT INTO releases VALUES ('plprofiler3-pg10', 'plprofiler', 'plProfiler', 'Procedural Language Performance Profiler', '', 'https://bitbucket.org/openscg/plprofiler', 'prod');

INSERT INTO releases VALUES ('cassandra_fdw3-pg10', 'cassandra_fdw', 'CassandraFDW', 'C* Interoperability', '', '', 'prod');

INSERT INTO releases VALUES ('hadoop_fdw2-pg10', 'hadoop_fdw', 'HadoopFDW', 'Hive Queries', '', '', 'prod');

INSERT INTO releases VALUES ('postgis25-pg11', 'postgis', 'PostGIS 2.5', 'Spatial & Geographic Objects', '', 'http://postgis.net/docs/manual-2.5', 'prod');
INSERT INTO releases VALUES ('postgis24-pg10', 'postgis', 'PostGIS 2.4', 'Spatial & Geographic Objects', '', 'http://postgis.net/docs/manual-2.4', 'prod');
INSERT INTO releases VALUES ('postgis23-pg96', 'postgis', 'PostGIS 2.3', 'Spatial & Geographic Objects', '', 'http://postgis.net/docs/manual-2.3', 'prod');
INSERT INTO releases VALUES ('postgis22-pg95', 'postgis', 'PostGIS 2.2', 'Spatial & Geographic Objects', '', 'http://postgis.net/docs/manual-2.2', 'prod');

INSERT INTO releases VALUES ('oracle_fdw2-pg10', 'oracle_fdw', 'OracleFDW', 'Foreign Data Wrapper for Oracle', '', 'https://github.com/laurenz/oracle_fdw', 'prod');

INSERT INTO releases VALUES ('mysql_fdw2-pg11', 'mysql_fdw', 'MySQL-FDW', 'Foreign Data Wrapper for MySQL', '', 'https://github.com/EnterpriseDB/mysql_fdw', 'prod');
INSERT INTO releases VALUES ('mysql_fdw2-pg10', 'mysql_fdw', 'MySQL-FDW', 'Foreign Data Wrapper for MySQL', '', 'https://github.com/EnterpriseDB/mysql_fdw', 'prod');

INSERT INTO releases VALUES ('tds_fdw1-pg96', 'tds_fdw', 'TDS-FDW', 'Foreign Data Wrapper for Sybase & SqlServer', '', 'https://github.com/tds-fdw/tds_fdw', 'prod');

INSERT INTO releases VALUES ('orafce3-pg10', 'orafce', 'OraFCE', 'Oracle Compatible Functions', '', 'https://github.com/orafce/orafce', 'prod');

INSERT INTO releases VALUES ('pljava15-pg95', 'pljava', 'plJava', 'Stored Procedures in Java', '', 'https://github.com/tada/pljava', 'prod');

INSERT INTO releases VALUES ('timescaledb-pg10', 'timescaledb', 'TimescaleDB', '', '', '', 'prod');

INSERT INTO releases VALUES ('hintplan-pg10', 'hintplan', 'HintPlan', 'Guiding the Planner w/ Hints', '', 'https://osdn.net/projects/pghintplan', 'prod');
INSERT INTO releases VALUES ('hintplan-pg96', 'hintplan', 'HintPlan', 'Guiding the Planner w/ Hints', '', 'https://osdn.net/projects/pghintplan', 'prod');
INSERT INTO releases VALUES ('hintplan-pg95', 'hintplan', 'HintPlan', 'Guiding the Planner w/ Hints', '', 'https://osdn.net/projects/pghintplan', 'prod');

INSERT INTO releases VALUES ('pgbdr94',    'pg',       'BDR Postgres 9.4', 'BDR-PG Server', '', 'http://bdr-project.org/docs/stable/', 'prod');
INSERT INTO releases VALUES ('grafana', 'grafana', 'Grafana', 'Monitoring & Metrics Dashboard', '', 'https://github.com/grafana/grafana', 'test');
INSERT INTO releases VALUES ('collectd5', 'collectd', 'collectd', 'Metrics Collection', '', 'http://collectd.org', 'test');


CREATE TABLE versions (
  component     TEXT    NOT NULL,
  version       TEXT    NOT NULL,
  platform      TEXT    NOT NULL,
  is_current    INTEGER NOT NULL,
  release_date  DATE    NOT NULL,
  parent        TEXT    NOT NULL,
  PRIMARY KEY (component, version),
  FOREIGN KEY (component) REFERENCES releases(component)
);

INSERT INTO versions VALUES ('hub', '4.0.4',       '', 1, '20181201', '');
INSERT INTO versions VALUES ('hub', '4.0.3',       '', 0, '20181029', '');
INSERT INTO versions VALUES ('hub', '4.0.2',       '', 0, '20181029', '');
INSERT INTO versions VALUES ('hub', '4.0.1',       '', 0, '20181027', '');
INSERT INTO versions VALUES ('hub', '4.0.0',       '', 0, '20181008', '');
INSERT INTO versions VALUES ('hub', '3.3.7',       '', 0, '20180920', '');

INSERT INTO versions VALUES ('bulkload3-pg10', '3.1.14-1', 'linux64',        1, '20171116', 'pg10');

INSERT INTO versions VALUES ('logical2-pg96', '2.0-1', 'linux64', 1, '20170518', 'pg96');

INSERT INTO versions VALUES ('pgpartman4-pg11', '4.0.0-1', 'linux64', 1, '20181108', 'pg11');
INSERT INTO versions VALUES ('pgpartman4-pg10', '4.0.0-1', 'linux64', 1, '20181108', 'pg10');
INSERT INTO versions VALUES ('pgpartman3-pg10', '3.1.1-1', 'linux64', 0, '20180208', 'pg10');

INSERT INTO versions VALUES ('repack14-pg11', '1.4.4-1', 'linux64', 1, '20181108', 'pg11');
INSERT INTO versions VALUES ('repack14-pg10', '1.4.4-1', 'linux64', 1, '20181108', 'pg10');
INSERT INTO versions VALUES ('repack14-pg10', '1.4.2-1', 'linux64', 0, '20171104', 'pg10');

INSERT INTO versions VALUES ('pgaudit12-pg10', '1.2.0-1', 'linux64', 1, '20171012', 'pg10');

INSERT INTO versions VALUES ('setuser1-pg10',  '1.4.0-1', 'linux64', 1, '20170831', 'pg10');

INSERT INTO versions VALUES ('pldebugger96-pg10',  '9.6.0-2', 'linux64', 1, '20170608', 'pg10');

INSERT INTO versions VALUES ('background1-pg96', '1.0-1', 'linux64', 1, '20170425', 'pg96');

INSERT INTO versions VALUES ('plprofiler3-pg10', '3.2-1', 'linux64', 1, '20180301', 'pg10');

INSERT INTO versions VALUES ('cassandra_fdw3-pg10', '3.1.3-1', 'linux64', 1, '20181011', 'pg10');

INSERT INTO versions VALUES ('hadoop_fdw2-pg10', '2.6.2-1', 'linux64', 1, '20181011', 'pg10');

INSERT INTO versions VALUES ('oracle_fdw2-pg10', '2.1.0-1', 'linux64', 1, '20181027', 'pg10');
INSERT INTO versions VALUES ('oracle_fdw2-pg10', '2.0.0-1', 'linux64', 0, '20160928', 'pg10');

INSERT INTO versions VALUES ('mysql_fdw2-pg11', '2.5.0-1', 'linux64',  1, '20181108', 'pg10');
INSERT INTO versions VALUES ('mysql_fdw2-pg10', '2.5.0-1', 'linux64',  1, '20181108', 'pg10');
INSERT INTO versions VALUES ('mysql_fdw2-pg10', '2.3.0-1', 'linux64',  0, '20170921', 'pg10');

INSERT INTO versions VALUES ('tds_fdw1-pg96', '1.0.8-1', 'linux64',  1, '20161123', 'pg96');

INSERT INTO versions VALUES ('orafce3-pg10', '3.6.1-1', 'linux64',  1, '20171104', 'pg10');

INSERT INTO versions VALUES ('pljava15-pg95', '1.5.0-1', 'linux64',  1, '20160701', 'pg95');

INSERT INTO versions VALUES ('timescaledb-pg10', '1.0.1-1',  'linux64', 1, '20181205', 'pg10');
INSERT INTO versions VALUES ('timescaledb-pg10', '1.0.0-1',  'linux64', 0, '20181031', 'pg10');

INSERT INTO versions VALUES ('hintplan-pg10', '1.3.1-1', 'linux64', 1, '20181027', 'pg10');
INSERT INTO versions VALUES ('hintplan-pg10', '1.3.0-1', 'linux64', 0, '20171116', 'pg10');

INSERT INTO versions VALUES ('postgis25-pg11', '2.5.0-1',   'linux64, osx64, win64', 1, '20181029', 'pg11');

INSERT INTO versions VALUES ('postgis24-pg10', '2.4.3-1',   'linux64, osx64, win64', 1, '20180208', 'pg10');
INSERT INTO versions VALUES ('postgis24-pg10', '2.4.2-1',   'linux64, osx64, win64', 0, '20171227', 'pg10');

INSERT INTO versions VALUES ('postgis23-pg96', '2.3.6-1', 'osx64, win64', 1, '20180208', 'pg96');
INSERT INTO versions VALUES ('postgis23-pg96', '2.3.3-2', 'osx64, win64', 0, '20170731', 'pg96');

INSERT INTO versions VALUES ('postgis22-pg95', '2.2.6-1', 'osx64, win64', 1, '20180208', 'pg95');
INSERT INTO versions VALUES ('postgis22-pg95', '2.2.5-1', 'osx64, win64', 0, '20170209', 'pg95');

INSERT INTO versions VALUES ('pgdg11', '11-1',  'linux64', 0, '20180920', '');
INSERT INTO versions VALUES ('pgdg10', '10-1',  'linux64', 0, '20160901', '');

INSERT INTO versions VALUES ('pg11', '11.1-2',     'linux64, osx64, win64', 1, '20181201', '');
INSERT INTO versions VALUES ('pg11', '11.1-1',     'linux64, osx64, win64', 0, '20181108', '');
INSERT INTO versions VALUES ('pg11', '11.0-1',     'linux64, osx64, win64', 0, '20181018', '');

INSERT INTO versions VALUES ('pg10', '10.6-1',     'linux64, osx64, win64', 1, '20181108', '');
INSERT INTO versions VALUES ('pg10', '10.5-1',     'linux64, osx64, win64', 0, '20180809', '');

INSERT INTO versions VALUES ('pg96', '9.6.11-1',   'osx64, win64', 1, '20181108', '');
INSERT INTO versions VALUES ('pg96', '9.6.10-1',   'osx64, win64', 0, '20180809', '');

INSERT INTO versions VALUES ('pg95', '9.5.15-1',   'osx64, win64', 1, '20181108', '');
INSERT INTO versions VALUES ('pg95', '9.5.14-1',   'osx64, win64', 0, '20180809', '');

INSERT INTO versions VALUES ('pg94', '9.4.20-1',   'osx64, win64', 1, '20181108', '');
INSERT INTO versions VALUES ('pg94', '9.4.19-1',   'osx64, win64', 0, '20180809', '');

INSERT INTO versions VALUES ('pgbdr94', '9.4.20-2',   'linux64',      1, '20181205', '');
INSERT INTO versions VALUES ('pgbdr94', '9.4.20-1',   'linux64',      0, '20181201', '');

INSERT INTO versions VALUES ('maria103', '10.3.10-1', 'linux64, win64', 1, '20181027', '');

INSERT INTO versions VALUES ('python2',    '2.7.11-4',    'win64',  1, '20160118', '');
INSERT INTO versions VALUES ('python37',   '3.7.1-2',     'win64',  1, '20181027', '');

INSERT INTO versions VALUES ('perl5',      '5.20.3.3',     'win64',  1, '20160314', '');

INSERT INTO versions VALUES ('tcl86',      '8.6.4-1',     'win64',    1, '20160311', '');

INSERT INTO versions VALUES ('pgadmin3',   '1.23.0b',   'win64, osx64',     1, '20170608', '');

INSERT INTO versions VALUES ('cassandra31',  '3.11.3',     '',             1, '20181018', '');
INSERT INTO versions VALUES ('spark23',  '2.3.1',          '',             1, '20180920', '');
INSERT INTO versions VALUES ('hadoop28',  '2.8.4',         '',             1, '20180920', '');
INSERT INTO versions VALUES ('hive23', '2.3.3',            '',             1, '20180920', '');
INSERT INTO versions VALUES ('presto', '0.206',            '',             1, '20180920', '');
INSERT INTO versions VALUES ('zookeeper34',  '3.4.12',     '',             1, '20180920', '');
INSERT INTO versions VALUES ('sqoop', '1.4.7',             '',             1, '20181008', '');

INSERT INTO versions VALUES ('collectd5',    '5.7.2-1',    'linux64', 1, '20181201', '');
INSERT INTO versions VALUES ('grafana',      '4.5.2',      'linux64', 1, '20181201', '');
