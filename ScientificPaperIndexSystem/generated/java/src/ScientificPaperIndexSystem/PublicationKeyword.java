package ScientificPaperIndexSystem;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class PublicationKeyword {
    private Number pid = 1L;
    private Number fosid = 1L;
    private String keyword = SeqUtil.toStr(SeqUtil.seq());

    public PublicationKeyword(final Number id, final Number fid, final String kw) {
        cg_init_PublicationKeyword_1(id, fid, kw);
    }

    public PublicationKeyword() {
    }

    public void cg_init_PublicationKeyword_1(final Number id, final Number fid,
        final String kw) {
        pid = id;
        fosid = fid;
        keyword = kw;

        return;
    }

    public void changeKeyword(final String kw) {
        keyword = kw;
    }

    public Number getPid() {
        return pid;
    }

    public Number getFosid() {
        return fosid;
    }

    public String getKeyword() {
        return keyword;
    }

    public String toString() {
        return "PublicationKeyword{" + "pid := " + Utils.toString(pid) +
        ", fosid := " + Utils.toString(fosid) + ", keyword := " +
        Utils.toString(keyword) + "}";
    }
}
