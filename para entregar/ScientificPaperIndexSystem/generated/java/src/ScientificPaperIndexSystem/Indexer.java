package ScientificPaperIndexSystem;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class Indexer {
    private VDMMap publications = MapUtil.map();
    private Number publicationIdCounter = 0L;
    private VDMMap authors = MapUtil.map();
    private Number authorIdCounter = 0L;
    private VDMMap affiliations = MapUtil.map();
    private Number affiliationIdCounter = 0L;
    private VDMMap fieldsofstudy = MapUtil.map();
    private Number fieldofstudyIdCounter = 0L;
    private VDMMap publicationkeywords = MapUtil.map();
    private Number publicationkeywordIdCounter = 0L;
    private VDMMap publicationauthoraffiliations = MapUtil.map();
    private Number paaIdCounter = 0L;
    private VDMMap publicationsreferences = MapUtil.map();
    private Number pReferencesIdCounter = 0L;
    private VDMMap publicationurls = MapUtil.map();

    public Indexer(final VDMMap pubs, final VDMMap auts, final VDMMap affs,
        final VDMMap fsos, final VDMMap pks, final VDMMap paas,
        final VDMMap prs, final VDMMap pus) {
        cg_init_Indexer_1(Utils.copy(pubs), Utils.copy(auts), Utils.copy(affs),
            Utils.copy(fsos), Utils.copy(pks), Utils.copy(paas),
            Utils.copy(prs), Utils.copy(pus));
    }

    public Indexer() {
        cg_init_Indexer_2();
    }

    public void cg_init_Indexer_1(final VDMMap pubs, final VDMMap auts,
        final VDMMap affs, final VDMMap fsos, final VDMMap pks,
        final VDMMap paas, final VDMMap prs, final VDMMap pus) {
        publications = Utils.copy(pubs);
        authors = Utils.copy(auts);
        affiliations = Utils.copy(affs);
        fieldsofstudy = Utils.copy(fsos);
        publicationkeywords = Utils.copy(pks);
        publicationauthoraffiliations = Utils.copy(paas);
        publicationsreferences = Utils.copy(prs);
        publicationurls = Utils.copy(pus);

        return;
    }

    public void cg_init_Indexer_2() {
        return;
    }

    public VDMMap getPublications() {
        return Utils.copy(publications);
    }

    public VDMMap getAuthors() {
        return Utils.copy(authors);
    }

    public VDMMap getAffiliations() {
        return Utils.copy(affiliations);
    }

    public VDMMap getFieldsofstudy() {
        return Utils.copy(fieldsofstudy);
    }

    public VDMMap getPublicationkeywords() {
        return Utils.copy(publicationkeywords);
    }

    public VDMMap getPublicationauthoraffiliations() {
        return Utils.copy(publicationauthoraffiliations);
    }

    public VDMMap getPublicationsreferences() {
        return Utils.copy(publicationsreferences);
    }

    public VDMMap getPublicationurls() {
        return Utils.copy(publicationurls);
    }

    public void insertAuthor(final String an, final VDMSet ints) {
        authorIdCounter = authorIdCounter.longValue() + 1L;
        authors = MapUtil.munion(Utils.copy(authors),
                MapUtil.map(
                    new Maplet(authorIdCounter, new Author(an, Utils.copy(ints)))));
    }

    public void insertAffiliation(final String affiname) {
        affiliationIdCounter = affiliationIdCounter.longValue() + 1L;
        affiliations = MapUtil.munion(Utils.copy(affiliations),
                MapUtil.map(
                    new Maplet(affiliationIdCounter, new Affiliation(affiname))));
    }

    public Author getAuthorById(final Number id) {
        return ((Author) Utils.get(authors, id));
    }

    public void addInterestsToAuthor(final Number aid, final VDMSet intr) {
        Author author = getAuthorById(aid);
        author.addInterests(Utils.copy(intr));
        authors = MapUtil.munion(Utils.copy(authors),
                MapUtil.map(new Maplet(aid, author)));
    }

    public void removeInterestsFromAuthor(final Number aid, final VDMSet intrs) {
        Author author = getAuthorById(aid);
        author.removeInterests(Utils.copy(intrs));
        authors = MapUtil.override(Utils.copy(authors),
                MapUtil.map(new Maplet(aid, author)));
    }

    public void insertPublication(final String ortitle, final Number pubyear,
        final Number pubdate, final String pubdoi, final Number pubrank,
        final Number idau, final VDMSet idcoauts, final Number idaf,
        final VDMSet idrefs) {
        publicationIdCounter = publicationIdCounter.longValue() + 1L;
        publications = MapUtil.munion(Utils.copy(publications),
                MapUtil.map(
                    new Maplet(publicationIdCounter,
                        new Publication(ortitle, pubyear, pubdate, pubdoi,
                            pubrank))));
        paaIdCounter = paaIdCounter.longValue() + 1L;
        publicationauthoraffiliations = MapUtil.munion(Utils.copy(
                    publicationauthoraffiliations),
                MapUtil.map(
                    new Maplet(paaIdCounter,
                        new PublicationAuthorAffiliation(publicationIdCounter,
                            idau, Utils.copy(idcoauts), idaf,
                            ((Affiliation) Utils.get(affiliations, idaf)).getName()))));
        insertReferences(publicationIdCounter, Utils.copy(idrefs));

        {
            VDMSet everypubauthor = SetUtil.union(SetUtil.set(idau),
                    Utils.copy(idcoauts));
            Number minerdos = 999L;
            Number minerdosauthorid = 9999999L;

            for (Iterator iterator_3 = everypubauthor.iterator();
                    iterator_3.hasNext();) {
                Number pubauthor = (Number) iterator_3.next();

                if (((Author) Utils.get(authors, pubauthor)).getErdos()
                         .longValue() < minerdos.longValue()) {
                    minerdos = ((Author) Utils.get(authors, pubauthor)).getErdos();
                    minerdosauthorid = pubauthor;
                }
            }

            for (Iterator iterator_4 = everypubauthor.iterator();
                    iterator_4.hasNext();) {
                Number pubauthor = (Number) iterator_4.next();

                if (!(Utils.equals(pubauthor, minerdosauthorid))) {
                    ((Author) Utils.get(authors, pubauthor)).setErdos(minerdos.longValue() +
                        1L);
                }
            }
        }
    }

    public VDMSet getPublicationsByAuthor(final Number ida) {
        VDMSet pubs = SetUtil.set();

        for (Iterator iterator_5 = MapUtil.rng(Utils.copy(
                        publicationauthoraffiliations)).iterator();
                iterator_5.hasNext();) {
            PublicationAuthorAffiliation pub = (PublicationAuthorAffiliation) iterator_5.next();

            if (Utils.equals(pub.getAutid(), ida)) {
                pubs = SetUtil.union(Utils.copy(pubs), SetUtil.set(pub));
            }
        }

        return Utils.copy(pubs);
    }

    public Number getAuthorByPublication(final Number idp) {
        Number aid = 0L;

        for (Iterator iterator_6 = MapUtil.rng(Utils.copy(
                        publicationauthoraffiliations)).iterator();
                iterator_6.hasNext();) {
            PublicationAuthorAffiliation pub = (PublicationAuthorAffiliation) iterator_6.next();

            if (Utils.equals(idp, pub.getPubid())) {
                aid = pub.getAutid();
            }
        }

        return aid;
    }

    public VDMSet getReferencesFromAuthorPublications(final VDMSet pubs) {
        VDMSet idpubs = SetUtil.set();

        for (Iterator iterator_7 = pubs.iterator(); iterator_7.hasNext();) {
            PublicationAuthorAffiliation pub = (PublicationAuthorAffiliation) iterator_7.next();
            idpubs = SetUtil.union(Utils.copy(idpubs),
                    SetUtil.set(pub.getPubid()));
        }

        {
            VDMSet idrefs = SetUtil.set();

            for (Iterator iterator_8 = MapUtil.rng(Utils.copy(
                            publicationsreferences)).iterator();
                    iterator_8.hasNext();) {
                PublicationReferences id = (PublicationReferences) iterator_8.next();
                idrefs = SetUtil.union(Utils.copy(idrefs), id.getPrids());
            }

            return Utils.copy(idrefs);
        }
    }

    public VDMSeq getCoauthorPath(final Number aid) {
        VDMSet pubs = getPublicationsByAuthor(aid);
        VDMSeq coauthpath = SeqUtil.seq();

        for (Iterator iterator_9 = pubs.iterator(); iterator_9.hasNext();) {
            PublicationAuthorAffiliation pub = (PublicationAuthorAffiliation) iterator_9.next();
            VDMSet coauths = pub.getCoautids();

            for (Iterator iterator_10 = coauths.iterator();
                    iterator_10.hasNext();) {
                Number coauth = (Number) iterator_10.next();
                Path path = new Path(pub.getPubid(),
                        ((Author) Utils.get(authors, coauth)).getName());
                coauthpath = SeqUtil.conc(Utils.copy(coauthpath),
                        SeqUtil.seq(path));
            }
        }

        return Utils.copy(coauthpath);
    }

    public Number getNumberSelfCitations(final Number ida) {
        VDMSet selfpubs = getPublicationsByAuthor(ida);

        {
            VDMSet idrefs = getReferencesFromAuthorPublications(Utils.copy(
                        selfpubs));

            {
                VDMSet idselfref = SetUtil.set();

                for (Iterator iterator_11 = idrefs.iterator();
                        iterator_11.hasNext();) {
                    Number refs = (Number) iterator_11.next();

                    if (Utils.equals(ida, getAuthorByPublication(refs))) {
                        idselfref = SetUtil.union(Utils.copy(idselfref),
                                SetUtil.set(refs));
                    }
                }

                return getReferencesFromAuthorPublications(Utils.copy(selfpubs))
                           .size();
            }
        }
    }

    public Number getTimesCited(final Number ida) {
        VDMSet authorpubs = getPublicationsByAuthor(ida);
        VDMSeq pubscited = SeqUtil.seq();

        for (Iterator iterator_12 = authorpubs.iterator();
                iterator_12.hasNext();) {
            PublicationAuthorAffiliation pubs = (PublicationAuthorAffiliation) iterator_12.next();

            for (Iterator iterator_13 = MapUtil.rng(Utils.copy(
                            publicationsreferences)).iterator();
                    iterator_13.hasNext();) {
                PublicationReferences references = (PublicationReferences) iterator_13.next();

                if (SetUtil.subset(SetUtil.set(pubs.getPubid()),
                            references.getPrids())) {
                    pubscited = SeqUtil.conc(Utils.copy(pubscited),
                            SeqUtil.seq(pubs.getPubid()));
                }
            }
        }

        return pubscited.size();
    }

    public void insertReferences(final Number idpub, final VDMSet idrefs) {
        pReferencesIdCounter = pReferencesIdCounter.longValue() + 1L;
        publicationsreferences = MapUtil.munion(Utils.copy(
                    publicationsreferences),
                MapUtil.map(
                    new Maplet(pReferencesIdCounter,
                        new PublicationReferences(idpub, Utils.copy(idrefs)))));
    }

    public String toString() {
        return "Indexer{" + "publications := " + Utils.toString(publications) +
        ", publicationIdCounter := " + Utils.toString(publicationIdCounter) +
        ", authors := " + Utils.toString(authors) + ", authorIdCounter := " +
        Utils.toString(authorIdCounter) + ", affiliations := " +
        Utils.toString(affiliations) + ", affiliationIdCounter := " +
        Utils.toString(affiliationIdCounter) + ", fieldsofstudy := " +
        Utils.toString(fieldsofstudy) + ", fieldofstudyIdCounter := " +
        Utils.toString(fieldofstudyIdCounter) + ", publicationkeywords := " +
        Utils.toString(publicationkeywords) +
        ", publicationkeywordIdCounter := " +
        Utils.toString(publicationkeywordIdCounter) +
        ", publicationauthoraffiliations := " +
        Utils.toString(publicationauthoraffiliations) + ", paaIdCounter := " +
        Utils.toString(paaIdCounter) + ", publicationsreferences := " +
        Utils.toString(publicationsreferences) + ", pReferencesIdCounter := " +
        Utils.toString(pReferencesIdCounter) + ", publicationurls := " +
        Utils.toString(publicationurls) + "}";
    }
}
