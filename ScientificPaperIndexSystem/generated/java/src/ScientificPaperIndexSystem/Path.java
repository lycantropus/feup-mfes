package ScientificPaperIndexSystem;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Path {
    private Number pubid = 0L;
    private String coauthname = SeqUtil.toStr(SeqUtil.seq());

    public Path(final Number pid, final String autname) {
        cg_init_Path_1(pid, autname);
    }

    public Path() {
    }

    public void cg_init_Path_1(final Number pid, final String autname) {
        pubid = pid;
        coauthname = autname;

        return;
    }

    public Number getpubid() {
        return pubid;
    }

    public String getcoauthname() {
        return coauthname;
    }

    public String toString() {
        return "Path{" + "pubid := " + Utils.toString(pubid) +
        ", coauthname := " + Utils.toString(coauthname) + "}";
    }
}
