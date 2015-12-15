package ScientificPaperIndexSystem;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Affiliation {
    private String name = SeqUtil.toStr(SeqUtil.seq());

    public Affiliation(final String an) {
        cg_init_Affiliation_1(an);
    }

    public Affiliation() {
    }

    public void cg_init_Affiliation_1(final String an) {
        name = an;

        return;
    }

    public void changeName(final String an) {
        name = an;
    }

    public String getName() {
        return name;
    }

    public String toString() {
        return "Affiliation{" + "name := " + Utils.toString(name) + "}";
    }
}
