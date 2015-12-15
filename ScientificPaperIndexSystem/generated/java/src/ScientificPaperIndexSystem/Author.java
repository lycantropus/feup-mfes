package ScientificPaperIndexSystem;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Author {
    private String name = SeqUtil.toStr(SeqUtil.seq());
    private VDMSet interests = SetUtil.set();
    private Number erdos = 9999L;

    public Author(final String an, final VDMSet ints) {
        cg_init_Author_1(an, Utils.copy(ints));
    }

    public Author(final String an, final VDMSet ints, final Number erdsnum) {
        cg_init_Author_2(an, Utils.copy(ints), erdsnum);
    }

    public Author() {
    }

    public void cg_init_Author_1(final String an, final VDMSet ints) {
        name = an;
        interests = Utils.copy(ints);

        return;
    }

    public void cg_init_Author_2(final String an, final VDMSet ints,
        final Number erdsnum) {
        name = an;
        interests = Utils.copy(ints);
        erdos = erdsnum;

        return;
    }

    public void changeName(final String an) {
        name = an;
    }

    public void addInterest(final String intr) {
        interests = SetUtil.union(Utils.copy(interests), SetUtil.set(intr));
    }

    public void addInterests(final VDMSet intr) {
        interests = SetUtil.union(Utils.copy(interests), Utils.copy(intr));
    }

    public void removeInterests(final VDMSet intrs) {
        interests = SetUtil.diff(Utils.copy(interests), Utils.copy(intrs));
    }

    public Number getErdos() {
        return erdos;
    }

    public void setErdos(final Number en) {
        erdos = en;
    }

    public String getName() {
        return name;
    }

    public VDMSet getInterests() {
        return Utils.copy(interests);
    }

    public String toString() {
        return "Author{" + "name := " + Utils.toString(name) +
        ", interests := " + Utils.toString(interests) + ", erdos := " +
        Utils.toString(erdos) + "}";
    }
}
