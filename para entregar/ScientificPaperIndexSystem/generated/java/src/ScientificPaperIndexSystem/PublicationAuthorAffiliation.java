package ScientificPaperIndexSystem;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class PublicationAuthorAffiliation {
    private Number pubid = 0L;
    private Number autid = 0L;
    private VDMSet coautids = SetUtil.set();
    private Number affid = 0L;
    private String oriaffnam = SeqUtil.toStr(SeqUtil.seq());

    public PublicationAuthorAffiliation(final Number puid, final Number auid,
        final VDMSet coaid, final Number afid, final String oan) {
        cg_init_PublicationAuthorAffiliation_1(puid, auid, Utils.copy(coaid),
            afid, oan);
    }

    public PublicationAuthorAffiliation() {
    }

    public void cg_init_PublicationAuthorAffiliation_1(final Number puid,
        final Number auid, final VDMSet coaid, final Number afid,
        final String oan) {
        pubid = puid;
        autid = auid;
        coautids = Utils.copy(coaid);
        affid = afid;
        oriaffnam = oan;

        return;
    }

    public void changeOriAffName(final String na) {
        oriaffnam = na;
    }

    public Number getPubid() {
        return pubid;
    }

    public Number getAutid() {
        return autid;
    }

    public VDMSet getCoautids() {
        return Utils.copy(coautids);
    }

    public Number getAffid() {
        return affid;
    }

    public String getOriAffName() {
        return oriaffnam;
    }

    public String toString() {
        return "PublicationAuthorAffiliation{" + "pubid := " +
        Utils.toString(pubid) + ", autid := " + Utils.toString(autid) +
        ", coautids := " + Utils.toString(coautids) + ", affid := " +
        Utils.toString(affid) + ", oriaffnam := " + Utils.toString(oriaffnam) +
        "}";
    }
}
