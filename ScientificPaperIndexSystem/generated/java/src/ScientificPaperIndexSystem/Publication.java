package ScientificPaperIndexSystem;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Publication {
    private String originalTitle = SeqUtil.toStr(SeqUtil.seq());
    private Number publishYear = 0L;
    private Number publishDate = 0L;
    private String doi = SeqUtil.toStr(SeqUtil.seq());
    private Number rank = 99999L;

    public Publication(final String opt, final Number ppy, final Number ppd,
        final String pdoi, final Number pr) {
        cg_init_Publication_1(opt, ppy, ppd, pdoi, pr);
    }

    public Publication() {
    }

    public void cg_init_Publication_1(final String opt, final Number ppy,
        final Number ppd, final String pdoi, final Number pr) {
        originalTitle = opt;
        publishYear = ppy;
        publishDate = ppd;
        doi = pdoi;
        rank = pr;

        return;
    }

    public void raiseRankBy(final Number nRanks) {
        rank = rank.longValue() - nRanks.longValue();
    }

    public void decreaseRankBy(final Number nRanks) {
        rank = rank.longValue() + nRanks.longValue();
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public Number getPublishYear() {
        return publishYear;
    }

    public Number getPublishDate() {
        return publishDate;
    }

    public String getDoi() {
        return doi;
    }

    public Number getRank() {
        return rank;
    }

    public String toString() {
        return "Publication{" + "originalTitle := " +
        Utils.toString(originalTitle) + ", publishYear := " +
        Utils.toString(publishYear) + ", publishDate := " +
        Utils.toString(publishDate) + ", doi := " + Utils.toString(doi) +
        ", rank := " + Utils.toString(rank) + "}";
    }
}
