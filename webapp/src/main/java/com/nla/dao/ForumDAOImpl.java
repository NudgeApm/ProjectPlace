package com.nla.dao;

import com.nla.domain.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.BadSqlGrammarException;
import org.springframework.jdbc.CannotGetJdbcConnectionException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

@Component
public class ForumDAOImpl implements ForumDAO {

    private JdbcTemplate jdbcTemplate;


    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    public void insertForum(KbUsr kbUsr) {
        String query = "INSERT INTO snapshot(ID, nom) VALUES (?,?)";
        jdbcTemplate.update(query, new Object[]{Integer.valueOf(kbUsr.getIdusr()), kbUsr.getUsrLogin()});
    }

    public KbUsr selectForum(int forumId) {
        String query = "SELECT * FROM ui_local.usr WHERE idusr=?";
        return (KbUsr) jdbcTemplate.queryForObject(query, new Object[]{Integer.valueOf(forumId)},
                new RowMapper() {
                    public Object mapRow(ResultSet resultSet, int rowNum) throws SQLException {
                        return new KbUsr(resultSet.getString("IDUSR"), resultSet.getString("USRLOGIN"));
                    }
                });
    }

    /**
     * load central base list
     *
     * @return List
     */
    public List<SchemaCb> getListSchemaCb() {
        List<SchemaCb> schemaList = null;
        try {
            String query = "select schemaname from pg_tables where  tablename ='dss_tree_info'";
            schemaList = jdbcTemplate.query(
                    query,
                    new Object[]{},
                    new RowMapper() {
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            SchemaCb acc = new SchemaCb(rs.getString("schemaname"));
                            return acc;
                        }
                    }
            );
        } catch (BadSqlGrammarException bge) { // ORACLE case
            String query = "SELECT distinct owner schemaname 	FROM all_tables where  lower(table_name) = 'dss_tree_info'";
            schemaList = jdbcTemplate.query(
                    query,
                    new Object[]{},
                    new RowMapper() {
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            SchemaCb acc = new SchemaCb(rs.getString("schemaname"));
                            return acc;
                        }
                    }
            );

        }
        return schemaList;
    }


    public List<SchemaKb> getListSchemaKB() {
        List<SchemaKb> schemaList = null;
        try {
            String query = "SELECT schemaname FROM pg_tables	where tablename like 'usr'";
            schemaList = jdbcTemplate.query(
                    query,
                    new Object[]{},
                    new RowMapper() {
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            SchemaKb acc = new SchemaKb(rs.getString("schemaname"));
                            return acc;
                        }
                    }
            );
        } catch (BadSqlGrammarException bge) { // ORACLE case
            String query = "SELECT distinct owner schemaname 	FROM all_tables where  lower(table_name) = 'usr'";
            schemaList = jdbcTemplate.query(
                    query,
                    new Object[]{},
                    new RowMapper() {
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            SchemaCb acc = new SchemaCb(rs.getString("schemaname"));
                            return acc;
                        }
                    }
            );

        }
        return schemaList;
    }

    @Override
    public void updateVersion(List<Schema> pListe) {
        String query = "";
        for (Schema s : pListe) {
            if ("".equals(query)) {
                query = "select  '" + s + "' centralBase , version S from " + s + ".sys_package_version, " + s + ".dss_objects where object_type_id = -102 and package_name = 'ADG_FULL_CENTRAL'";
            } else {
                query += " UNION ALL ";
                query += "select  '" + s + "' centralBase , version S from " + s + ".sys_package_version, " + s + ".dss_objects where object_type_id = -102 and package_name = 'ADG_FULL_CENTRAL'";
            }
        }
        ArrayList<StringBean> listVersion = (ArrayList<StringBean>) jdbcTemplate.query(query, new MapperManager.StringBeanMapper());
        if (listVersion != null && listVersion.size() > 0) {
            for (StringBean sb : listVersion) {
                for (Schema s : pListe) {
                    if (((SchemaCb) s).getName().equals(sb.getCentralBase())) {
                        s.setVersion(sb.getS());
                    }
                }

            }
        }
    }

    /*
     * update KB declared in Central base
     * @see com.nla.dao.ForumDAO#updateLocalSite(java.util.List)
     */
    @Override
    public void updateLocalSite(List<Schema> pListe) {
        String query = "";
        for (Schema s : pListe) {
            if ("".equals(query)) {
                query = "select  '" + s + "' centralBase , local_dss_name S from " + s + ".dss_sites where site_type = 2";
            } else {
                query += " UNION ALL ";
                query += "select  '" + s + "' centralBase , local_dss_name S from " + s + ".dss_sites where site_type = 2";
            }
        }
        ArrayList<StringBean> listVersion = (ArrayList<StringBean>) jdbcTemplate.query(query, new MapperManager.StringBeanMapper());
        if (listVersion != null && listVersion.size() > 0) {
            for (StringBean sb : listVersion) {
                for (Schema s : pListe) {
                    if (((SchemaCb) s).getName().equals(sb.getCentralBase())) {
                        ((SchemaCb) s).getLocalSiteList().add(sb.getS());
                    }
                }

            }
        }
    }

    @Override
    public void updateSnapshot(List<Schema> pListe) {
        String query = "";
        for (Schema s : pListe) {
            if ("".equals(query)) {
                query = "select '" + s + "' centralBase , snapshot_name, snapshot_id, object_id, object_name, functional_date from " + s + ".dss_snapshots , " + s + ".dss_objects where dss_objects.object_id = dss_snapshots.application_id";
            } else {
                query += " UNION ALL ";
                query += "select '" + s + "' centralBase , snapshot_name, snapshot_id, object_id, object_name, functional_date from " + s + ".dss_snapshots , " + s + ".dss_objects where dss_objects.object_id = dss_snapshots.application_id";
            }
        }
        ArrayList<Snapshot> snapshot = (ArrayList<Snapshot>) jdbcTemplate.query(query, new MapperManager.SnapshotMapper());
        if (snapshot != null && snapshot.size() > 0) {
            for (Snapshot sn : snapshot) {
                for (Schema s : pListe) {
                    if (((SchemaCb) s).getName().equals(sn.getCentralBase())) {
                        //((SchemaCb)s).getLocalSiteList().add(sb.getS());
                        ((SchemaCb) s).getSnapshotList().add(sn);
                    }
                }

            }
        }


//		for(Schema s: pListe){
//			ArrayList<Snapshot> snap = (ArrayList<Snapshot>)jdbcTemplate.query("select '"+s+"' centralBase , snapshot_name, snapshot_id, object_id, object_name, functional_date from "+s+".dss_snapshots , "+s+".dss_objects where dss_objects.object_id = dss_snapshots.application_id", new MapperManager.SnapshotMapper());	
//			if(snap != null && snap.size() > 0){
//				((SchemaCb)s).setSnapshotList(snap);
//			}
//		}
    }


    @Override
    public void updateEnlightenViews(List<Schema> pListe) {
        for (Schema s : pListe) {
            String query = "select local_dss_name from " + s + ".dss_sites where site_type = 2";
            List<String> listLocalSite = jdbcTemplate.query(
                    query,
                    new Object[]{},
                    new RowMapper() {
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            String ss = rs.getString("local_dss_name");
                            return ss;
                        }
                    }
            );

            if (listLocalSite != null && listLocalSite.size() > 0) {
                String kbName = listLocalSite.get(0);
                List<SchemaKb> schemaList;
                try {
                    query = "SELECT schemaname FROM pg_tables	where tablename like 'usr' and schemaname = '" + kbName + "'";
                    schemaList = jdbcTemplate.query(
                            query,
                            new Object[]{},
                            new RowMapper() {
                                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                                    SchemaKb acc = new SchemaKb(rs.getString("schemaname"));
                                    return acc;
                                }
                            }
                    );

                } catch (BadSqlGrammarException bge) { // ORACLE case
                    query = "SELECT distinct owner schemaname 	FROM all_tables where  lower(table_name) = 'usr' and owner = '" + kbName + "'";
                    schemaList = jdbcTemplate.query(
                            query,
                            new Object[]{},
                            new RowMapper() {
                                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                                    SchemaCb acc = new SchemaCb(rs.getString("schemaname"));
                                    return acc;
                                }
                            }
                    );

                }


                if (schemaList != null && schemaList.size() != 0) {
                    query = "select idmod, modnam from  " + kbName + ".mod";
                    List<EnlightenView> listEnlightenView = jdbcTemplate.query(
                            query,
                            new Object[]{},
                            new RowMapper() {
                                public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                                    EnlightenView ev = new EnlightenView();
                                    ev.setModname(rs.getString("modnam"));
                                    ev.setIdmod(rs.getInt("idmod"));
                                    return ev;
                                }
                            }
                    );
                    if (listEnlightenView != null && listEnlightenView.size() > 0) {
                        ((SchemaCb) s).setEnlightenViewList((ArrayList<EnlightenView>) listEnlightenView);
                    }
                }
            }
        }
    }


    @Override
    public List<Violation> getListViolation(final String pCentralBaseName, final String pSnapshotIDApplicationID, final int pMetricID) {
        String userPostgres = pCentralBaseName;
        StringTokenizer st = new StringTokenizer(pSnapshotIDApplicationID, ",");
        String snapshotID = st.nextToken();
        String applicationID = st.nextToken();

        ArrayList<String> listLocalSite = (ArrayList<String>) jdbcTemplate.query("select local_dss_name S from " + userPostgres + ".dss_sites where site_type = 2", new MapperManager.StringSMapper());

        String kbName = listLocalSite.get(0);


        String query = "SELECT DISTINCT CD.diag_id LiId,CD.object_id objID, CD.object_name LiName, modObj.object_name objModuleName, refp.path LiPath,  dsp.line_start LineBegin ";
        query = query + " FROM   " + userPostgres + ".dss_module_links ml, " + userPostgres + ".dss_objects modObj, " + userPostgres + ".dss_link_info l, " + userPostgres + ".csv_diagdetails CD  left join " + userPostgres + ".dss_translation_table tt ON ( tt.object_id = CD.object_id ) ";
        query = query + " left join " + kbName + ".objfilref objfil  ON ( tt.site_object_id = objfil.idobj ) left join " + kbName + ".refpath refp ON ( refp.idfilref = objfil.idfilref ) left join " + kbName + ".dss_source_positions dsp";
        query = query + " ON ( dsp.object_id = tt.site_object_id ) ";
        query = query + " WHERE  CD.diag_id IN( " + String.valueOf(pMetricID) + " ) ";
        query = query + " AND L.previous_object_id = ML.module_id ";
        query = query + " AND modObj.object_id = ml.object_id ";
        query = query + " AND l.next_object_id = cd.object_id ";
        query = query + " AND modObj.object_type_id = 20000 ";
        query = query + " AND CD.snapshot_id = " + snapshotID;
        query = query + " AND ( CD.context_id = " + applicationID;
        query = query + " OR CD.context_id IN (SELECT OM.object_id ";
        query = query + " FROM   " + userPostgres + ".dss_links LM ";
        query = query + " inner join " + userPostgres + ".dss_objects OM ON ";
        query = query + " LM.next_object_id = OM.object_id ";
        query = query + " inner join " + userPostgres + ".dss_objects OA  ON ";
        query = query + " LM.previous_object_id = OA.object_id ";
        query = query + " WHERE  OA.object_id = " + applicationID;
        query = query + " AND OM.object_type_id = 20000) ) ORDER  BY liname";


        List<Violation> violationList = jdbcTemplate.query(
                query,
                new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Violation v = new Violation();
                        v.setFilePath(rs.getString("lipath"));
                        v.setLoc(rs.getInt("LineBegin"));
                        v.setModule(rs.getString("objModuleName"));
                        v.setName(rs.getString("liName"));
                        v.setObjId(rs.getInt("objID"));
                        v.setMetricID(rs.getInt("LiId"));
                        return v;
                    }
                }
        );

        ArrayList<Action> actionList = (ArrayList<Action>) jdbcTemplate.query("select metric_id, object_id, user_name, sel_date, priority, action_def from " + pCentralBaseName + ".viewer_action_plans where metric_id IN( " + String.valueOf(pMetricID) + " ) ", new MapperManager.ActionMapper());

        for (Violation v : violationList) {
            for (Action a : actionList) {
                if (v.getObjId() == a.getObjectId() && v.getMetricID() == a.getRuleId()) {
                    v.setSelectedForAP(true);
                }
            }
        }


        return violationList;
    }


    @Override
    public List<ObjectPoc> getListObj(final String pCentralBaseName, final String pSnapshotIDApplicationID,
                                      final String fhForm, final String weightForm, final String thresholdtForm, final String criticaltForm, final String moduleForm,
                                      final String objNameForm, final String metricNameForm, final String metricIDForm) {
        String userPostgres = pCentralBaseName;
        StringTokenizer st = new StringTokenizer(pSnapshotIDApplicationID, ",");
        String snapshotID = st.nextToken();
        String query = "SELECT DISTINCT results.OBJECT_ID ObjId, obj.OBJECT_FULL_NAME ObjFullname,  results.METRIC_ID MtId,  cqt.METRIC_NAME MtName,  cqt.m_weight,  modObj.object_name modObj, " +
                " (SELECT COALESCE (   (SELECT CASE     WHEN oprev.OBJECT_CHECKSUM Is Null     THEN 1     WHEN oprev.OBJECT_CHECKSUM = ocur.OBJECT_CHECKSUM     THEN 0     ELSE 3    END    FROM " + userPostgres + ".VIEWER_DUAL),    1)  " +
                " FROM " + userPostgres + ".DSS_OBJECT_INFO ocur   LEFT JOIN " + userPostgres + ".DSS_OBJECT_INFO oprev  ON (oprev.SNAPSHOT_ID = -1) " +
                " WHERE (ocur.OBJECT_ID = obj.OBJECT_ID And ocur.SNAPSHOT_ID = 1 AND oprev.OBJECT_ID = obj.OBJECT_ID) ) ObjStatus, (SELECT cplx.METRIC_NUM_VALUE  " +
                " FROM   " + userPostgres + ".DSS_METRIC_RESULTS cplx  WHERE cplx.METRIC_ID = 65005    AND obj.OBJECT_ID = cplx.OBJECT_ID    AND cplx.SNAPSHOT_ID = 1 ) ObjComplexity,  " +
                " (CASE (SELECT COALESCE (MIN (1), 0)        FROM   " + userPostgres + ".DSS_METRIC_RESULTS dmr2        WHERE dmr2.METRIC_ID = Results.METRIC_ID          AND dmr2.OBJECT_ID = results.OBJECT_ID         " +
                " AND dmr2.SNAPSHOT_ID = -1)    WHEN 0 THEN 'New'    ELSE 'Old'    END) MtViolationStatus,   (CASE parent.METRIC_PARENT_ID   WHEN 60011 THEN 0    WHEN 60012 THEN dor.FAN_IN    " +
                " ELSE dor.HIT_COUNT   END) MtRPF,   (CASE parent.METRIC_PARENT_ID    WHEN 60011 THEN dor.TRAN_PROPER    WHEN 60012 THEN dor.CHAN_PROPER    WHEN 60013 THEN dor.ROBU_PROPER   " +
                " WHEN 60014 THEN dor.PERF_PROPER    WHEN 60016 THEN dor.SECU_PROPER    ELSE 0   END) MtVI,   (CASE parent.METRIC_PARENT_ID    WHEN 60011       " +
                " THEN dor.TRAN_PROPER    WHEN 60012       THEN dor.CHAN_PROPER * (1.0 + dor.FAN_IN)    WHEN 60013       THEN dor.ROBU_PROPER * (1.0 + dor.HIT_COUNT)   " +
                " WHEN 60014       THEN dor.PERF_PROPER * (1.0 + dor.HIT_COUNT)    WHEN 60016       THEN dor.SECU_PROPER * (1.0 + dor.HIT_COUNT)    ELSE 0    END) MtPRI,  " +
                " parent.METRIC_PARENT_ID MtParentId   FROM " + userPostgres + ".DSS_OBJECTS obj,    " + userPostgres + ".DSS_METRIC_RESULTS results,    " + userPostgres + ".DSS_METRIC_TYPE_TREES metric,    " + userPostgres + ".DSS_METRIC_TYPE_TREES parent,   " +
                " " + userPostgres + ".DSS_OBJECT_RANKING dor,    " + userPostgres + ".CSV_QUALITY_TREE cqt,    " + userPostgres + ".dss_module_links ml,   " + userPostgres + ".dss_link_info l,   " + userPostgres + ".dss_objects modObj WHERE results.METRIC_ID = metric.METRIC_ID + 1     " +
                " AND parent.METRIC_PARENT_ID = " + fhForm + "     AND metric.METRIC_PARENT_ID = parent.METRIC_ID     AND results.SNAPSHOT_ID = " + snapshotID + "    AND dor.SNAPSHOT_ID = " + snapshotID +
                " AND L.previous_object_id = ML.module_id AND modObj.object_id = ml.object_id AND modObj.object_type_id = 20000 AND l.next_object_id = obj.object_id    " +
                " AND results.METRIC_VALUE_INDEX = 1     AND cqt.METRIC_ID = metric.METRIC_ID     AND obj.OBJECT_ID = results.OBJECT_ID  and cqt.m_weight >" + weightForm + "    AND dor.OBJECT_ID = obj.OBJECT_ID  ";


        if ("true".equals(criticaltForm)) {
            query = query + "    AND metric.METRIC_CRITICAL = 1 ";
        }

        if (moduleForm != null && !"".equals(moduleForm)) {
            query = query + "   AND modObj.OBJECT_NAME like '%" + moduleForm + "%'";
        }

        if (moduleForm != null && !"".equals(moduleForm)) {
            query = query + "   AND modObj.OBJECT_NAME like '%" + moduleForm + "%'";
        }


        if (objNameForm != null && !"".equals(objNameForm)) {
            query = query + " AND obj.OBJECT_FULL_NAME like '%" + objNameForm + "%'";
        }

        if (metricNameForm != null && !"".equals(metricNameForm)) {
            query = query + " AND cqt.METRIC_NAME like '%" + metricNameForm + "%'";
        }

        if (metricIDForm != null && !"".equals(metricIDForm)) {
            query = query + " AND results.METRIC_ID =" + metricIDForm;
        }


        String queryAddORA = "   AND ROWNUM < " + thresholdtForm;
        String queryORDERBY = " ORDER BY MtPRI  DESC";
        String queryAddCSS = "   LIMIT " + thresholdtForm;

        String queryCSS = query + queryORDERBY + queryAddCSS;
        String queryORA = query + queryAddORA + queryORDERBY;
        List<ObjectPoc> objectList = null;
        try {
            objectList = jdbcTemplate.query(
                    queryCSS,
                    new Object[]{},
                    new RowMapper() {
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            ObjectPoc o = new ObjectPoc();
                            o.setMetricID(rs.getInt("mtid"));
                            o.setMetricName(rs.getString("mtname"));
                            o.setObjFullName(rs.getString("objfullname"));
                            o.setObjID(rs.getInt("objid"));
                            o.setmWeight(rs.getInt("m_weight"));
                            o.setModuleName(rs.getString("modobj"));
                            o.setStatus(rs.getString("mtviolationstatus"));
                            o.setPri(rs.getInt("mtpri"));
                            o.setHf(rs.getInt("mtparentid"));
                            return o;
                        }
                    }
            );
        } catch (BadSqlGrammarException badSQL) {
            objectList = jdbcTemplate.query(
                    queryORA,
                    new Object[]{},
                    new RowMapper() {
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            ObjectPoc o = new ObjectPoc();
                            o.setMetricID(rs.getInt("mtid"));
                            o.setMetricName(rs.getString("mtname"));
                            o.setObjFullName(rs.getString("objfullname"));
                            o.setObjID(rs.getInt("objid"));
                            o.setmWeight(rs.getInt("m_weight"));
                            o.setModuleName(rs.getString("modobj"));
                            o.setStatus(rs.getString("mtviolationstatus"));
                            o.setPri(rs.getInt("mtpri"));
                            o.setHf(rs.getInt("mtparentid"));
                            return o;
                        }
                    }
            );
        }

        return objectList;
    }

    @Override
    public List<Metric> getListMetric(final String pCentralBaseName, final String pSnapshotIDApplicationID) {
        String userPostgres = pCentralBaseName;
        StringTokenizer st = new StringTokenizer(pSnapshotIDApplicationID, ",");
        String snapshotID = st.nextToken();
        String applicationID = st.nextToken();
        String query = "select distinct(METRIC_ID) mtid, critical,  B_CRITERION_NAME, T_CRITERION_NAME, M_weight weight, METRIC_NAME mtname, note mark,    sum(Detail) checkkO,   ";
        query = query + " (sum(total)-sum(detail)) checkOK,    sum(Total) Total,note_ct, weight_ct, critical_ct, B_CRITERION_NAME, Metric_description, b_criterion_id, t_criterion_id";
        query = query + " from  ( ";
        query = query + " select    distinct(CQT.METRIC_NAME), CQT.METRIC_ID, CQT.M_weight, CQT.T_CRITERION_NAME, CQT.B_CRITERION_NAME, DMR_DTAIL.METRIC_NUM_VALUE Detail, ";
        query = query + " round(CAST(DMR_NOTE.METRIC_NUM_VALUE AS decimal) ,2  ) note,  DMTT.METRIC_CRITICAL Critical, DMR_TOTAL.METRIC_NUM_VALUE Total, ";
        query = query + " DMR_CT.METRIC_NUM_VALUE note_ct, CQT.t_weight weight_ct,CQT.t_crit critical_ct, Metric_description,b_criterion_id, t_criterion_id ";
        query = query + " from " + userPostgres + ".CSV_QUALITY_TREE CQT, " + userPostgres + ".DSS_METRIC_RESULTS DMR_DTAIL, " + userPostgres + ".DSS_METRIC_RESULTS DMR_NOTE, " + userPostgres + ".DSS_METRIC_RESULTS DMR_TOTAL,  ";
        query = query + " " + userPostgres + ".DSS_METRIC_RESULTS DMR_CT, " + userPostgres + ".DSS_METRIC_TYPE_TREES DMTT, " + userPostgres + ".DSS_METRIC_TYPES DMT where CQT.METRIC_ID = dmr_Dtail.METRIC_ID  ";
        query = query + " and CQT.METRIC_ID = DMTT.METRIC_ID and DMR_DTAIL.SNAPSHOT_ID = " + snapshotID + " and DMR_DTAIL.METRIC_VALUE_INDEX = 1   ";
        query = query + " and CQT.METRIC_ID = DMR_TOTAL.METRIC_ID  and DMR_TOTAL.SNAPSHOT_ID = " + snapshotID + " and DMR_TOTAL.METRIC_VALUE_INDEX =  2 ";
        query = query + " and DMR_DTAIL.OBJECT_ID=DMR_TOTAL.OBJECT_ID  and CQT.METRIC_ID = DMR_NOTE.METRIC_ID and DMR_NOTE.SNAPSHOT_ID =  " + snapshotID + " ";
        query = query + " and DMR_NOTE.METRIC_VALUE_INDEX = 0  and dmr_note.object_id =  " + applicationID + " and dmr_Dtail.object_id in (select distinct(dss_ml2.module_id) ";
        query = query + " from " + userPostgres + ".dss_module_links dss_ml2                             where dss_ml2.object_id = " + applicationID + ") and CQT.B_CRITERION_ID in (60011, 60012, 60013, 60014, 60016) ";
        query = query + " and DMR_CT.OBJECT_ID =" + applicationID + " and DMR_CT.METRIC_VALUE_INDEX = 0 and DMR_CT.SNAPSHOT_ID=  " + snapshotID + " and DMR_CT.metric_id = CQT.T_criterion_id  ";
        query = query + " and DMT.metric_id = DMR_DTAIL.metric_id )  ";

        String queryCSSAlias = " as foo  ";

        String groupBy = " group by  METRIC_ID,METRIC_NAME, note, Critical , T_CRITERION_NAME,M_weight, B_CRITERION_NAME, note_ct, weight_ct, critical_ct, Metric_description, ";
        groupBy = groupBy + " b_criterion_id, t_criterion_id  ";
        groupBy = groupBy + " order by 2 desc,5 desc,7 desc ";

        String queryCSS = query + queryCSSAlias + groupBy;
        String queryORA = query + groupBy;

        List<Metric> metricList = null;
        try {
            metricList = jdbcTemplate.query(
                    queryCSS,
                    new Object[]{},
                    new RowMapper() {
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Metric m = new Metric(rs.getString("mtname"));
                            m.setId(rs.getInt("mtid"));
                            m.setWeight(rs.getInt("weight"));
                            m.setGrade(rs.getDouble("mark"));
                            m.setCritical(rs.getBoolean("critical"));
                            m.setHealthFactor(rs.getString("B_CRITERION_NAME"));
                            m.setTechnicalCriteria(rs.getString("T_CRITERION_NAME"));
                            m.setCheckKO(rs.getInt("checkkO"));
                            m.setCheckOK(rs.getInt("checkok"));
                            m.setTcGrade(rs.getDouble("note_ct"));
                            m.setTcWeight(rs.getInt("weight_ct"));
                            m.setDescription(rs.getString("metric_description"));
                            m.setHfId(rs.getInt("b_criterion_id"));
                            m.setTcId(rs.getInt("t_criterion_id"));
                            return m;
                        }
                    }
            );
        } catch (BadSqlGrammarException badSQL) {
            metricList = jdbcTemplate.query(
                    queryORA,
                    new Object[]{},
                    new RowMapper() {
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            Metric m = new Metric(rs.getString("mtname"));
                            m.setId(rs.getInt("mtid"));
                            m.setWeight(rs.getInt("weight"));
                            m.setGrade(rs.getDouble("mark"));
                            m.setCritical(rs.getBoolean("critical"));
                            m.setHealthFactor(rs.getString("B_CRITERION_NAME"));
                            m.setTechnicalCriteria(rs.getString("T_CRITERION_NAME"));
                            m.setCheckKO(rs.getInt("checkkO"));
                            m.setCheckOK(rs.getInt("checkok"));
                            m.setTcGrade(rs.getDouble("note_ct"));
                            m.setTcWeight(rs.getInt("weight_ct"));
                            m.setDescription(rs.getString("metric_description"));
                            m.setHfId(rs.getInt("b_criterion_id"));
                            m.setTcId(rs.getInt("t_criterion_id"));
                            return m;
                        }
                    }
            );
        }


        ArrayList<Action> actionList = (ArrayList<Action>) jdbcTemplate.query("select '" + pCentralBaseName + "' centralBase , metric_id, object_id, user_name, sel_date, priority, action_def from " + pCentralBaseName + ".viewer_action_plans", new MapperManager.ActionMapper());
        for (Metric m : metricList) {
            for (Action a : actionList) {
                if (m.getId() == a.getRuleId()) {
                    m.addActionToActionList(a);
                }
            }

        }

        return metricList;
    }

    @Override
    public List<SchemaMNGT> getListSchemaMNGT() {
        List<SchemaMNGT> schemaList = null;
        try {
            String query = "select schemaname from pg_tables where  tablename ='cms_am_models'";
            schemaList = jdbcTemplate.query(
                    query,
                    new Object[]{},
                    new RowMapper() {
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            SchemaMNGT acc = new SchemaMNGT(rs.getString("schemaname"));
                            return acc;
                        }
                    }
            );
        } catch (BadSqlGrammarException bge) { // ORACLE case
            String query = "SELECT distinct owner schemaname 	FROM all_tables where  lower(table_name) = 'cms_am_models'";
            schemaList = jdbcTemplate.query(
                    query,
                    new Object[]{},
                    new RowMapper() {
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            SchemaMNGT acc = new SchemaMNGT(rs.getString("schemaname"));
                            return acc;
                        }
                    }
            );

        }
        return schemaList;
    }


    @Override
    public void updateTechnologies(List<Schema> pListe) {
        String query = "";
        for (Schema s : pListe) {
            for (Snapshot snap : ((SchemaCb) s).getSnapshotList()) {
                if ("".equals(query)) {
                    query = "  Select '" + s + "' centralBase ," + snap.getId() + " snapshotID, O.OBJECT_ID, O.OBJECT_TYPE_ID,  O.OBJECT_NAME, T.OBJECT_TYPE_NAME ";
                    query += " FROM " + s + ".DSS_OBJECT_TYPES T," + s + ".DSS_OBJECTS O, " + s + ".DSS_LINKS I";
                    query += " WHERE O.OBJECT_ID = I.NEXT_OBJECT_ID ";
                    query += " AND T.OBJECT_TYPE_ID = O.OBJECT_TYPE_ID ";
                    query += " AND exists (SELECT * FROM " + s + ".DSS_METRIC_RESULTS MR ";
                    query += " WHERE MR.OBJECT_ID=O.OBJECT_ID AND ";
                    query += " MR.SNAPSHOT_ID = " + snap.getId();
                    query += " AND MR.METRIC_VALUE_INDEX=0 ";
                    query += " AND MR.METRIC_ID = 60017 ) ";
                    query += " AND I.LINK_TYPE_ID = 2 ";
                    query += " AND I.PREVIOUS_OBJECT_ID = " + snap.getAppli().getId();
                    //query += " ORDER BY 4 ASC";
                } else {
                    query += " UNION ALL ";
                    query += " Select '" + s + "' centralBase ," + snap.getId() + " snapshotID, O.OBJECT_ID, O.OBJECT_TYPE_ID,  O.OBJECT_NAME, T.OBJECT_TYPE_NAME ";
                    query += " FROM " + s + ".DSS_OBJECT_TYPES T," + s + ".DSS_OBJECTS O, " + s + ".DSS_LINKS I";
                    query += " WHERE O.OBJECT_ID = I.NEXT_OBJECT_ID ";
                    query += " AND T.OBJECT_TYPE_ID = O.OBJECT_TYPE_ID ";
                    query += " AND exists (SELECT * FROM " + s + ".DSS_METRIC_RESULTS MR ";
                    query += " WHERE MR.OBJECT_ID=O.OBJECT_ID AND ";
                    query += " MR.SNAPSHOT_ID = " + snap.getId();
                    query += " AND MR.METRIC_VALUE_INDEX=0 ";
                    query += " AND MR.METRIC_ID = 60017 ) ";
                    query += " AND I.LINK_TYPE_ID = 2 ";
                    query += " AND I.PREVIOUS_OBJECT_ID = " + snap.getAppli().getId();
                    //query += " ORDER BY 4 ASC";
                }
            }
        }
        List<Technology> technoList = jdbcTemplate.query(query, new MapperManager.TechnologyMapper());
        if (technoList != null && technoList.size() > 0) {
            for (Technology t : technoList) {
                for (Schema s : pListe) {
                    for (Snapshot snap : ((SchemaCb) s).getSnapshotList()) {
                        if (((SchemaCb) s).getName().equals(t.getCentralBase()) && snap.getId() == t.getSnapshotID()) {
                            snap.getListTechnologies().add(t);
                        }
                    }
                }
            }
        }

//		for(Schema s: pListe){
//			for(Snapshot snap:((SchemaCb)s).getSnapshotList()){
//				String query = "Select '"+s+"' centralBase ,"+snap.getId()+" snapshotID, O.OBJECT_ID, O.OBJECT_TYPE_ID,  O.OBJECT_NAME, T.OBJECT_TYPE_NAME "; 
//						query += " FROM "+s+".DSS_OBJECT_TYPES T,"+s+".DSS_OBJECTS O, "+s+".DSS_LINKS I"; 
//						query += " WHERE O.OBJECT_ID = I.NEXT_OBJECT_ID ";
//						query += " AND T.OBJECT_TYPE_ID = O.OBJECT_TYPE_ID "; 
//						query += " AND exists (SELECT * FROM "+s+".DSS_METRIC_RESULTS MR "; 
//						query += " WHERE MR.OBJECT_ID=O.OBJECT_ID AND ";
//						query += " MR.SNAPSHOT_ID = "+snap.getId();
//						query += " AND MR.METRIC_VALUE_INDEX=0 ";
//						query += " AND MR.METRIC_ID = 60017 ) ";
//						query += " AND I.LINK_TYPE_ID = 2 ";
//						query += " AND I.PREVIOUS_OBJECT_ID = "+snap.getAppli().getId();
//						query += " ORDER BY 4 ASC";
//				List<Technology> technoList = jdbcTemplate.query( query, new MapperManager.TechnologyMapper());				
//				snap.setListTechnologies((ArrayList<Technology>)technoList);
//			}
//		}
//		
    }

    @Override
    public void updateVolumes(List<Schema> pListe) {
        String query = "";
        for (Schema s : pListe) {
            for (Snapshot snap : ((SchemaCb) s).getSnapshotList()) {
                if ("".equals(query)) {
                    query = "Select '" + s + "' centralBase ," + snap.getId() + " snapshotID, DMT.METRIC_ID , DMR.METRIC_NUM_VALUE valeur From " + s + ".DSS_METRIC_TYPES DMT, " + s + ".DSS_METRIC_RESULTS DMR";
                    query += " where DMR.METRIC_ID = DMT.METRIC_ID  and DMR.METRIC_ID in (10151,10107)";
                    query += " and DMR.OBJECT_ID = " + snap.getAppli().getId();
                    query += " and DMR.METRIC_VALUE_INDEX = 1 and DMR.SNAPSHOT_ID = " + snap.getId();
                    query += " and DMR.METRIC_NUM_VALUE != 0 ";
                } else {
                    query += " UNION ALL ";
                    query += "Select '" + s + "' centralBase ," + snap.getId() + " snapshotID, DMT.METRIC_ID , DMR.METRIC_NUM_VALUE valeur From " + s + ".DSS_METRIC_TYPES DMT, " + s + ".DSS_METRIC_RESULTS DMR";
                    query += " where DMR.METRIC_ID = DMT.METRIC_ID  and DMR.METRIC_ID in (10151,10107)";
                    query += " and DMR.OBJECT_ID = " + snap.getAppli().getId();
                    query += " and DMR.METRIC_VALUE_INDEX = 1 and DMR.SNAPSHOT_ID = " + snap.getId();
                    query += " and DMR.METRIC_NUM_VALUE != 0 ";
                }
            }
        }
        ArrayList<Contribution> listContribution = (ArrayList<Contribution>) jdbcTemplate.query(query, new MapperManager.ContributionMapper());
        if (listContribution != null && listContribution.size() > 0) {
            for (Contribution c : listContribution) {
                for (Schema s : pListe) {
                    for (Snapshot snap : ((SchemaCb) s).getSnapshotList()) {
                        if (((SchemaCb) s).getName().equals(c.getCentralBase()) && snap.getId() == c.getSnapshotID()) {
                            snap.getListHealthFactor().add(c);
                        }
                    }
                }
            }
        }
//		for(Schema s: pListe){
//			for(Snapshot snap:((SchemaCb)s).getSnapshotList()){
//				String query = "Select '"+s+"' centralBase ,"+snap.getId()+" snapshotID, DMT.METRIC_ID , DMR.METRIC_NUM_VALUE valeur From "+s+".DSS_METRIC_TYPES DMT, "+s+".DSS_METRIC_RESULTS DMR"; 
//						query += " where DMR.METRIC_ID = DMT.METRIC_ID  and DMR.METRIC_ID in (10151,10107)"; 
//						query += " and DMR.OBJECT_ID = "+snap.getAppli().getId();
//						query += " and DMR.METRIC_VALUE_INDEX = 1 and DMR.SNAPSHOT_ID = "+snap.getId();
//						query += " and DMR.METRIC_NUM_VALUE != 0 order by 1";
//				List<Contribution> contributionList = jdbcTemplate.query(query, new MapperManager.ContributionMapper());		    
//				
//				snap.getListHealthFactor().addAll((ArrayList<Contribution>)contributionList);
//			}
//		}

    }

    @Override
    public void updateHealthFactors(List<Schema> pListe) {
        String query = "";
        for (Schema s : pListe) {
            for (Snapshot snap : ((SchemaCb) s).getSnapshotList()) {
                if ("".equals(query)) {
                    query = "select  '" + s + "' centralBase ," + snap.getId() + " snapshotID, t1.METRIC_ID, round(cast(r1.METRIC_NUM_VALUE as decimal),2) valeur from " + s + ".DSS_METRIC_RESULTS r1, " + s + ".DSS_METRIC_TYPE_TREES t1 	where r1.OBJECT_ID = " + snap.getAppli().getId() + " and r1.SNAPSHOT_ID = " + snap.getId() + " and r1.METRIC_VALUE_INDEX = 0	and r1.METRIC_ID = t1.METRIC_ID and t1.METRIC_PARENT_ID = 60010";
                } else {
                    query += " UNION ALL ";
                    query += "select  '" + s + "' centralBase ," + snap.getId() + " snapshotID, t1.METRIC_ID, round(cast(r1.METRIC_NUM_VALUE as decimal),2) valeur from " + s + ".DSS_METRIC_RESULTS r1, " + s + ".DSS_METRIC_TYPE_TREES t1 	where r1.OBJECT_ID = " + snap.getAppli().getId() + " and r1.SNAPSHOT_ID = " + snap.getId() + " and r1.METRIC_VALUE_INDEX = 0	and r1.METRIC_ID = t1.METRIC_ID and t1.METRIC_PARENT_ID = 60010";
                }
            }
        }
        ArrayList<Contribution> listContribution = (ArrayList<Contribution>) jdbcTemplate.query(query, new MapperManager.ContributionMapper());
        if (listContribution != null && listContribution.size() > 0) {
            for (Contribution c : listContribution) {
                for (Schema s : pListe) {
                    for (Snapshot snap : ((SchemaCb) s).getSnapshotList()) {
                        if (((SchemaCb) s).getName().equals(c.getCentralBase()) && snap.getId() == c.getSnapshotID()) {
                            snap.getListHealthFactor().add(c);
                        }
                    }
                }
            }
        }

//		for(Schema s: pListe){
//			for(Snapshot snap:((SchemaCb)s).getSnapshotList()){
//				final String query = "Select t1.METRIC_ID, round(cast(r1.METRIC_NUM_VALUE as decimal),2) valeur from "+s+".DSS_METRIC_RESULTS r1, "+s+".DSS_METRIC_TYPE_TREES t1 	where r1.OBJECT_ID = "+snap.getAppli().getId()+" and r1.SNAPSHOT_ID = "+snap.getId()+" and r1.METRIC_VALUE_INDEX = 0	and r1.METRIC_ID = t1.METRIC_ID and t1.METRIC_PARENT_ID = 60010";
//				List<Contribution> contributionList = jdbcTemplate.query(
//				    query,
//				    new Object[]{},
//				    new RowMapper() {
//				        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
//				        	Contribution c = new Contribution();
//				        	c.setId(rs.getInt("METRIC_ID"));
//				        	c.setGrade(rs.getDouble("valeur"));
//				            return c;
//				        }
//				    }
//				);
//				
//				snap.setListHealthFactor((ArrayList<Contribution>)contributionList);
//			}
//		}

    }

    @Override
    public void updateCentralBaseList(List<SchemaMNGT> pListe) {
        for (Schema s : pListe) {
            ArrayList<String> listCB = (ArrayList<String>) jdbcTemplate.query("select distinct(cb.object_name) S from " + s + ".cms_inf_snapshot du, " + s + ".cms_inf_css_centraldb cb where du.central_id = cb.object_id", new MapperManager.StringSMapper());
            if (listCB != null) {
                for (String ss : listCB) {
                    if (ss != null) {
                        ((SchemaMNGT) s).getListCB().add(ss);
                    }
                }
            }

        }
    }

    @Override
    public void updateKnowledgeBaseList(List<Schema> pListe) {
        for (Schema s : pListe) {
            ArrayList<String> listKB = (ArrayList<String>) jdbcTemplate.query("select kb.object_name S from " + s + ".cms_deliveryunit du, " + s + ".cms_inf_css_localdb kb where du.object_id = kb.deliveryunit_id", new MapperManager.StringSMapper());
            if (listKB != null) {
                for (String ss : listKB) {
                    ((SchemaMNGT) s).getListKB().add(ss);
                }
            }
        }
    }


    @Override
    public void updateApplicationAndModule(List<SchemaMNGT> pListe) {
        for (SchemaMNGT s : pListe) {
            ArrayList<Application> applicationList = (ArrayList<Application>) jdbcTemplate.query("select object_id,object_name, '" + s + "' centralBaseName from  " + s + ".cms_portf_application ", new MapperManager.ApplicationMapper());

            s.setListApplication(applicationList);

            ArrayList<Module> moduleList = (ArrayList<Module>) jdbcTemplate.query("select object_id,object_name,application_id from " + s + ".cms_portf_module ", new MapperManager.ModuleMapper());
            for (Module m : moduleList) {
                for (Application a : ((SchemaMNGT) s).getListApplication()) {
                    if (m.getIdApp() == a.getId()) {
                        a.getModuleList().add(m);
                    }
                }

            }


        }
    }


    @Override
    public void updateActions(List<Schema> pListe) {

        String query = "";
        for (Schema s : pListe) {
            if ("".equals(query)) {
                query = "select  '" + s + "' centralBase ,  metric_id, object_id, user_name, sel_date, priority, action_def from " + s + ".viewer_action_plans";
            } else {
                query += " UNION ALL ";
                query += "select  '" + s + "' centralBase ,  metric_id, object_id, user_name, sel_date, priority, action_def from " + s + ".viewer_action_plans";
            }
        }
        ArrayList<Action> listAction = (ArrayList<Action>) jdbcTemplate.query(query, new MapperManager.ActionMapper());
        if (listAction != null && listAction.size() > 0) {
            for (Action a : listAction) {
                for (Schema s : pListe) {
                    if (((SchemaCb) s).getName().equals(a.getCentralBase())) {
                        ((SchemaCb) s).getActionList().add(a);
                    }
                }

            }
        }
    }


    @Override
    public List<Metric> getListMetricForEnlightenView(String schemaName, int enlightenID) {

        String query = "select local_dss_name from " + schemaName + ".dss_sites where site_type = 2";
        List<String> listLocalSite = jdbcTemplate.query(
                query,
                new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        String ss = rs.getString("local_dss_name");
                        return ss;
                    }
                }
        );
        String kbName = "";
        if (listLocalSite != null && listLocalSite.size() > 0) {
            kbName = listLocalSite.get(0);
        }

        query = " select distinct dmt.metric_name as Rule_name, dmttp.aggregate_weight as Agreggation_weight, dmttp.metric_critical as Critical, count (distinct O1.object_id) as nb_violations,  dmttpp.metric_parent_id  ";
        query = query + " from " + kbName + ".dss_metric_results dmr, " + kbName + ".MODKEY mk, " + kbName + ".cdt_objects O1, " + kbName + ".dss_metric_types dmt, " + kbName + ".dss_metric_type_trees dmtt, " + kbName + ".dss_metric_type_trees dmttp, " + kbName + ".dss_metric_type_trees dmttpp, " + kbName + ".dssapp_artifacts a ";
        query = query + " where ";
        query = query + " O1.object_id = mk.idkey ";
        query = query + " and a.object_id = O1.object_id ";
        query = query + " and O1.object_id = dmr.object_id ";
        query = query + " and dmr.metric_id = dmt.metric_id ";
        query = query + " and dmt.metric_id = dmtt.metric_id ";
        query = query + " and dmttp.metric_id = dmtt.metric_parent_id ";
        query = query + " and dmttpp.metric_id = dmttp.metric_parent_id ";
        query = query + " and dmt.metric_group = 2 ";
        query = query + " and dmttpp.metric_parent_id in (60014,60016,60013) ";
        query = query + " and O1.object_id in ( ";
        query = query + " select idkey from " + kbName + ".modkey where idmod = " + enlightenID + " ) ";
        query = query + " group by dmt.metric_name, dmttp.aggregate_weight,dmttp.metric_critical,dmttpp.metric_parent_id ";
        query = query + " order by dmttp.aggregate_weight desc; ";
        List<Metric> metricList = jdbcTemplate.query(
                query,
                new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Metric m = new Metric(rs.getString("rule_name"));
                        m.setWeight(rs.getInt("Agreggation_weight"));
                        m.setCritical(rs.getBoolean("critical"));
                        m.setCheckKO(rs.getInt("nb_violations"));
                        m.setHfId(rs.getInt("metric_parent_id"));
                        return m;
                    }
                }
        );
        return metricList;
    }

    @Override
    public ArrayList<Metric> getActionListFromCB(String pSchema) {
        String query = "select vap.metric_id, dmt.metric_name, vap.object_id, vap.user_name, vap.sel_date, vap.priority, vap.action_def, ds.object_name, ds.object_full_name from " + pSchema + ".viewer_action_plans vap, " + pSchema + ".dss_metric_types dmt, " + pSchema + ".dss_objects ds ";
        query = query + " where dmt.metric_id = vap.metric_id ";
        query = query + " and ds.object_id = vap.object_id ";


        ArrayList<Action> actionList = (ArrayList<Action>) jdbcTemplate.query(
                query,
                new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Action a = new Action();
                        a.setRuleId(rs.getInt("metric_id"));
                        String ruleName = rs.getString("metric_name");
                        String temp = ruleName.replaceAll("'", "").replace("\"", "");
                        a.setRuleName(temp);
                        a.setObjectId(rs.getInt("object_id"));
                        a.setObjectFullName(rs.getString("object_full_name"));
                        a.setSetDate(rs.getString("sel_date"));
                        a.setPriority(rs.getString("priority"));
                        return a;
                    }
                }
        );
        HashMap<Integer, Metric> metricInPAList = new HashMap<Integer, Metric>();
        for (Action a : actionList) {
            Metric tempM = metricInPAList.get(a.getRuleId());
            if (tempM == null) {
                Metric m = new Metric(a.getRuleName());
                m.setId(a.getRuleId());
                m.addActionToActionList(a);
                metricInPAList.put(a.getRuleId(), m);
            } else {
                tempM.addActionToActionList(a);
            }
        }

        ArrayList<Metric> metricPAList = new ArrayList<Metric>();
        for (Metric m : metricInPAList.values()) {
            metricPAList.add(m);
        }

        return metricPAList;

    }

    @Override
    public void deleteFromAP(String pSchema, String pMetricID, String pObjID) {
        if ("0".equals(pObjID)) {
            jdbcTemplate.update("DELETE from " + pSchema + ".viewer_action_plans where metric_id= ? ",
                    new Object[]{Integer.valueOf(pMetricID).intValue()});
        } else {
            jdbcTemplate.update("DELETE from " + pSchema + ".viewer_action_plans where metric_id= ? AND object_id = ?",
                    new Object[]{Integer.valueOf(pMetricID).intValue(), Integer.valueOf(pObjID).intValue()});
        }
    }

    @Override
    public boolean addObjToActionPlan(String pSchemaName,
                                      String snapshotIDslashappliID, Integer pMetricID, String objID,
                                      String priority, String comment) {
        StringTokenizer st = new StringTokenizer(snapshotIDslashappliID, ",");
        String snapshotID = st.nextToken();

        StringTokenizer stObjList = new StringTokenizer(objID, ",");
        while (stObjList.hasMoreElements()) {
            String objIDtoInsert = (String) stObjList.nextElement();

            try { // version 7.0
                jdbcTemplate.update("INSERT INTO " + pSchemaName + ".VIEWER_ACTION_PLANS(metric_id, object_id, first_snapshot_id, last_snapshot_id, user_name, sel_date,priority, action_def)  VALUES (?,?,?,?,?,?,?,?);",
                        new Object[]{pMetricID, Integer.valueOf(objIDtoInsert).intValue(), Integer.valueOf(snapshotID).intValue(), 1000000000, 1, Calendar.getInstance(), Integer.valueOf(priority).intValue(), comment});
            } catch (org.springframework.jdbc.BadSqlGrammarException bad) {
                // version 7.1
                jdbcTemplate.update("INSERT INTO " + pSchemaName + ".VIEWER_ACTION_PLANS(metric_id, object_id, first_snapshot_date, last_snapshot_date, user_name, sel_date,priority, action_def)  VALUES (?,?,?,?,?,?,?,?);",
                        new Object[]{pMetricID, Integer.valueOf(objIDtoInsert).intValue(), new Date(), new Date(), 1, Calendar.getInstance(), Integer.valueOf(priority).intValue(), comment});

            }
        }
        return true;
    }

    @Override
    public void updateSchemaWithRemovedMetric(Schema pSchema, Schema pSchemaRef) {
        String query = "select mt1.METRIC_ID ,mt1.METRIC_NAME as NAME, mt1.METRIC_DESCRIPTION as DESCRIPTION, mt1.METRIC_TYPE as TYPE ,mt1.METRIC_GROUP as GROUP ";
        query += "from " + pSchemaRef.getName() + ".DSS_METRIC_TYPES mt1 ";
        query += "where not exists (select 1 from " + pSchema.getName() + ".DSS_METRIC_TYPES mt2 where mt1.METRIC_ID = mt2.METRIC_ID) and mt1.METRIC_GROUP=1 ";


        ArrayList<Metric> metricList = (ArrayList<Metric>) jdbcTemplate.query(
                query,
                new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Metric m = new Metric(rs.getString("NAME"));
                        m.setDescription(rs.getString("DESCRIPTION"));
                        m.setId(rs.getInt("METRIC_ID"));
                        return m;
                    }
                }
        );
        ((SchemaCb) pSchema).setRemovedMetric(metricList);

    }

    @Override
    public void updateSchemaWithAddedMetric(Schema pSchema, Schema pSchemaRef) {
        String query = "select mt1.METRIC_ID ,mt1.METRIC_NAME as NAME, mt1.METRIC_DESCRIPTION as DESCRIPTION, mt1.METRIC_TYPE as TYPE ,mt1.METRIC_GROUP as GROUP ";
        query += "from " + pSchema.getName() + ".DSS_METRIC_TYPES mt1 ";
        query += "where not exists (select 1 from " + pSchemaRef.getName() + ".DSS_METRIC_TYPES mt2 where mt1.METRIC_ID = mt2.METRIC_ID) and mt1.METRIC_GROUP=1 ";


        ArrayList<Metric> metricList = (ArrayList<Metric>) jdbcTemplate.query(
                query,
                new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Metric m = new Metric(rs.getString("NAME"));
                        m.setDescription(rs.getString("DESCRIPTION"));
                        m.setId(rs.getInt("METRIC_ID"));
                        return m;
                    }
                }
        );
        ((SchemaCb) pSchema).setAddedMetric(metricList);

    }

    @Override
    public void updateSchemaDisabledMetric(Schema pSchema, Schema pSchemaRef) {
        String query = "select mt1.METRIC_ID ,mt1.METRIC_NAME as NAME, mt1.METRIC_DESCRIPTION as DESCRIPTION , ";
        query += " mt1.METRIC_TYPE as TYPE ,mt2.METRIC_TYPE as refTYPE from " + pSchema.getName() + ".DSS_METRIC_TYPES mt1 ," + pSchemaRef.getName() + ".DSS_METRIC_TYPES mt2 ";
        query += " where mt1.METRIC_ID = mt2.METRIC_ID and mt1.METRIC_TYPE = 0 and mt1.METRIC_TYPE <> mt2.METRIC_TYPE";


        ArrayList<Metric> metricList = (ArrayList<Metric>) jdbcTemplate.query(
                query,
                new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Metric m = new Metric(rs.getString("NAME"));
                        m.setDescription(rs.getString("DESCRIPTION"));
                        m.setId(rs.getInt("METRIC_ID"));
                        return m;
                    }
                }
        );
        ((SchemaCb) pSchema).setDisabledMetric(metricList);
    }

    @Override
    public void updateSchemaParamModified(Schema pSchema, Schema pSchemaRef) {
        String query = "Select 	mt1.METRIC_ID ";
        query += " ,dmt.metric_name as name ";
        query += " ,mt1.STATUS ";
        query += " ,mt2.STATUS ";
        query += " ,mt1.THRESHOLD_1 ";
        query += " ,mt2.THRESHOLD_1 ";
        query += " ,mt1.THRESHOLD_2 ";
        query += " ,mt2.THRESHOLD_2 ";
        query += " ,mt1.THRESHOLD_3 ";
        query += " ,mt2.THRESHOLD_3 ";
        query += " ,mt1.THRESHOLD_4 ";
        query += " ,mt2.THRESHOLD_4 ";
        query += " From " + pSchema.getName() + ".DSS_METRIC_STATUS_THRESHOLDS mt1 ";
        query += " ," + pSchemaRef.getName() + ".DSS_METRIC_STATUS_THRESHOLDS mt2, " + pSchema.getName() + ".DSS_METRIC_TYPES dmt ";
        query += " where mt1.METRIC_ID = mt2.METRIC_ID ";
        query += " and mt1.metric_id = dmt.metric_id ";
        query += " and (mt1.THRESHOLD_1 <> mt2.THRESHOLD_1 ";
        query += " or mt1.THRESHOLD_2 <> mt2.THRESHOLD_2 ";
        query += " or mt1.THRESHOLD_3 <> mt2.THRESHOLD_3 ";
        //query += " or mt1.THRESHOLD_4 <> mt2.THRESHOLD_4)";
        query += " or trunc(mt1.THRESHOLD_4*10) <> trunc(mt2.THRESHOLD_4*10))";

        query += " and mt1.metric_id<60000 ";


        ArrayList<Metric> metricList = (ArrayList<Metric>) jdbcTemplate.query(
                query,
                new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Metric m = new Metric(rs.getString("NAME"));
                        m.setId(rs.getInt("METRIC_ID"));
                        return m;
                    }
                }
        );
        ((SchemaCb) pSchema).setParamModifyMetric(metricList);

    }

    @Override
    public void updateSchemaParamModifiedWithSTD(Schema pSchema) {
        String query = "Select ";
        query += " me.metric_description, ";
        query += " mt1.METRIC_ID ";
        query += " ,mt1.STATUS ";
        query += " ,mt1.THRESHOLD_1 ";
        query += " ,mt1.THRESHOLD_2 ";
        query += " ,mt1.THRESHOLD_3 ";
        query += " ,mt1.THRESHOLD_4 ";
        query += " From " + pSchema.getName() + ".DSS_METRIC_STATUS_THRESHOLDS mt1 ";
        query += " , " + pSchema.getName() + ".dss_metric_descriptions me ";
        query += " where mt1.metric_id<60000 ";
        query += " and mt1.metric_id = me.metric_id ";
        query += " and me.description_type_id = 0 ";
        query += " and me.language = 'ENGLISH' ";


        ArrayList<Metric> metricList = (ArrayList<Metric>) jdbcTemplate.query(
                query,
                new Object[]{},
                new RowMapper() {
                    public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                        Metric m = new Metric(rs.getInt("METRIC_ID"),
                                rs.getString("metric_description"),
                                rs.getInt("STATUS"),
                                rs.getFloat("THRESHOLD_1"),
                                rs.getFloat("THRESHOLD_2"),
                                rs.getFloat("THRESHOLD_3"),
                                rs.getFloat("THRESHOLD_4")
                        );
                        return m;

                    }
                }
        );


        ArrayList<Metric> metricListThreshold = new ArrayList<Metric>();
        ArrayList<Metric> metricListAdded = new ArrayList<Metric>();
        for (Metric m : metricList) {
            if (m.isthresholdModified()) {
                metricListThreshold.add(m);
            }

            if (m.isAdded()) {
                metricListAdded.add(m);
            }
        }


        ((SchemaCb) pSchema).setAddedMetric(metricListAdded);
        ((SchemaCb) pSchema).setParamModifyMetricSTD(metricListThreshold);

    }

    @Override
    public boolean isConnectionOK() {
        try {
            String query = "select 1 test";
            List<String> listEnlightenView = jdbcTemplate.query(
                    query,
                    new Object[]{},
                    new RowMapper() {
                        public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                            return rs.getString("test");
                        }
                    }
            );
        } catch (CannotGetJdbcConnectionException cjce) {
            try {
                String query = "select 1 test from dual";
                List<String> listEnlightenView = jdbcTemplate.query(
                        query,
                        new Object[]{},
                        new RowMapper() {
                            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                                return String.valueOf(rs.getInt("test"));
                            }
                        }
                );
            } catch (CannotGetJdbcConnectionException cjcx) {
                return false;
            }
        } catch (BadSqlGrammarException bge) {
            try {
                String query = "select 1 test from dual";
                List<String> listEnlightenView = jdbcTemplate.query(
                        query,
                        new Object[]{},
                        new RowMapper() {
                            public Object mapRow(ResultSet rs, int rowNum) throws SQLException {
                                return String.valueOf(rs.getInt("test"));
                            }
                        }
                );
            } catch (CannotGetJdbcConnectionException cjcx) {
                return false;
            }
        }
        return true;
    }

}
