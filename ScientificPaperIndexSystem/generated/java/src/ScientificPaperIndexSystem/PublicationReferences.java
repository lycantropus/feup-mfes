package ScientificPaperIndexSystem;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class PublicationReferences {
    private Number pid = 0L;
    private VDMSet prids = SetUtil.set();

    public PublicationReferences(final Number id, final VDMSet rids) {
        cg_init_PublicationReferences_1(id, Utils.copy(rids));
    }

    public PublicationReferences() {
    }

    public void cg_init_PublicationReferences_1(final Number id,
        final VDMSet rids) {
        pid = id;
        prids = Utils.copy(rids);

        return;
    }

    public void addReference(final Number pubid) {
        prids = SetUtil.union(Utils.copy(prids), SetUtil.set(pubid));
    }

    public void removeReference(final Number prid) {
        prids = SetUtil.diff(Utils.copy(prids), SetUtil.set(prid));
    }

    public Number getPid() {
        return pid;
    }

    public VDMSet getPrids() {
        return Utils.copy(prids);
    }

    public String toString() {
        return "PublicationReferences{" + "pid := " + Utils.toString(pid) +
        ", prids := " + Utils.toString(prids) + "}";
    }
}
