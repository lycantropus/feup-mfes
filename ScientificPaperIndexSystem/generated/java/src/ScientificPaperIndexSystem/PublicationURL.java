package ScientificPaperIndexSystem;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class PublicationURL {
    private Number pid = 0L;
    private String url = SeqUtil.toStr(SeqUtil.seq());

    public PublicationURL(final Number id, final String u) {
        cg_init_PublicationURL_1(id, u);
    }

    public PublicationURL() {
    }

    public void cg_init_PublicationURL_1(final Number id, final String u) {
        pid = id;
        url = u;

        return;
    }

    public Number getPid() {
        return pid;
    }

    public String getUrl() {
        return url;
    }

    public String toString() {
        return "PublicationURL{" + "pid := " + Utils.toString(pid) +
        ", url := " + Utils.toString(url) + "}";
    }
}
