package ScientificPaperIndexSystem;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class FieldOfStudy {
    private String name = SeqUtil.toStr(SeqUtil.seq());

    public FieldOfStudy(final String an) {
        cg_init_FieldOfStudy_1(an);
    }

    public FieldOfStudy() {
    }

    public void cg_init_FieldOfStudy_1(final String an) {
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
        return "FieldOfStudy{" + "name := " + Utils.toString(name) + "}";
    }
}
