package ScientificPaperIndexSystem;

import org.overture.codegen.runtime.*;

import java.util.*;


@SuppressWarnings("all")
public class TestIndexer extends MyTestCase {
    public TestIndexer() {
    }

    public void testAffiliation() {
        Affiliation af = new Affiliation("FEUP");
        super.assertEqual("FEUP", af.getName());
    }

    public void testAffiliationChangeName() {
        Affiliation af = new Affiliation("FEUP");
        super.assertEqual("FEUP", af.getName());
        af.changeName("MIT");
        super.assertEqual("MIT", af.getName());
    }

    public void testAuthor() {
        Author a = new Author("Andre",
                SetUtil.set("amputacoes", "desmembramentos"));
        super.assertEqual("Andre", a.getName());
        super.assertEqual(SetUtil.set("amputacoes", "desmembramentos"),
            a.getInterests());
    }

    public void testAuthorChangeName() {
        Author a = new Author("Andre",
                SetUtil.set("amputacoes", "desmembramentos"));
        super.assertEqual("Andre", a.getName());
        a.changeName("Banana");
        super.assertEqual("Banana", a.getName());
    }

    public void testAddInterestsAuthor() {
        Author a = new Author("Andre",
                SetUtil.set("amputacoes", "desmembramentos"));
        super.assertEqual(SetUtil.set("amputacoes", "desmembramentos"),
            a.getInterests());
        a.addInterests(SetUtil.set("mutilaÃ§oes", "imolaÃ§oes"));
        super.assertEqual(SetUtil.set("amputacoes", "desmembramentos",
                "mutilaÃ§oes", "imolaÃ§oes"), a.getInterests());
    }

    public void testRemoveInterestsAuthor() {
        Author a = new Author("Andre",
                SetUtil.set("amputacoes", "desmembramentos"));
        super.assertEqual(SetUtil.set("amputacoes", "desmembramentos"),
            a.getInterests());
        a.removeInterests(SetUtil.set("amputacoes"));
        super.assertEqual(SetUtil.set("desmembramentos"), a.getInterests());
        a.removeInterests(SetUtil.set("desmembramentos"));
        super.assertEqual(SetUtil.set(), a.getInterests());
    }

    public void testFieldOfStudy() {
        FieldOfStudy fos = new FieldOfStudy("Necromancia");
        super.assertEqual("Necromancia", fos.getName());
    }

    public void testPublication() {
        Publication pub = new Publication("A Specifier's Introduction to Formal Methods",
                1990L, 901L, "11.111.11/ISBN", 100L);
        super.assertEqual("A Specifier's Introduction to Formal Methods",
            pub.getOriginalTitle());
        super.assertEqual(1990L, pub.getPublishYear());
        super.assertEqual(901L, pub.getPublishDate());
        super.assertEqual("11.111.11/ISBN", pub.getDoi());
        super.assertEqual(100L, pub.getRank());
    }

    public void testRaiseRankBy() {
        Publication pub = new Publication("A Specifier's Introduction to Formal Methods",
                1990L, 901L, "11.111.11/ISBN", 100L);
        super.assertEqual(100L, pub.getRank());
        pub.raiseRankBy(50L);
        super.assertEqual(50L, pub.getRank());
    }

    public void testDecreaseRankBy() {
        Publication pub = new Publication("A Specifier's Introduction to Formal Methods",
                1990L, 901L, "11.111.11/ISBN", 100L);
        super.assertEqual(100L, pub.getRank());
        pub.decreaseRankBy(50L);
        super.assertEqual(150L, pub.getRank());
    }

    public void testPublicationAuthorAffiliation() {
        PublicationAuthorAffiliation paa = new PublicationAuthorAffiliation(1L,
                2L, SetUtil.set(1L, 3L, 4L), 3L, "FEUP");
        super.assertEqual(1L, paa.getPubid());
        super.assertEqual(2L, paa.getAutid());
        super.assertEqual(3L, paa.getAffid());
        super.assertEqual("FEUP", paa.getOriAffName());
    }

    public void testPublicationAuthorAffiliationChangeName() {
        PublicationAuthorAffiliation paa = new PublicationAuthorAffiliation(1L,
                2L, SetUtil.set(1L, 3L, 4L), 3L, "FEUP");
        super.assertEqual("FEUP", paa.getOriAffName());
        paa.changeOriAffName("MIT");
        super.assertEqual("MIT", paa.getOriAffName());
    }

    public void testPublicationKeyword() {
        PublicationKeyword pk = new PublicationKeyword(1L, 2L, "MFES");
        super.assertEqual(1L, pk.getPid());
        super.assertEqual(2L, pk.getFosid());
        super.assertEqual("MFES", pk.getKeyword());
    }

    public void testPublicationKeywordChangeKeyword() {
        PublicationKeyword pk = new PublicationKeyword(1L, 2L, "MFES");
        pk.changeKeyword("SEFM");
        super.assertEqual("SEFM", pk.getKeyword());
    }

    public void testPublicationReferences() {
        PublicationReferences pr = new PublicationReferences(1L,
                SetUtil.set(2L, 3L));
        super.assertEqual(1L, pr.getPid());
        super.assertEqual(SetUtil.set(2L, 3L), pr.getPrids());
    }

    public void testPublicationReferencesAddReference() {
        PublicationReferences pr = new PublicationReferences(1L,
                SetUtil.set(2L, 3L));
        super.assertEqual(1L, pr.getPid());
        super.assertEqual(SetUtil.set(2L, 3L), pr.getPrids());
        pr.addReference(4L);
        super.assertEqual(SetUtil.set(2L, 3L, 4L), pr.getPrids());
    }

    public void testPublicationReferencesRemoveReference() {
        PublicationReferences pr = new PublicationReferences(1L,
                SetUtil.set(2L, 3L, 4L));
        super.assertEqual(1L, pr.getPid());
        super.assertEqual(SetUtil.set(2L, 3L, 4L), pr.getPrids());
        pr.removeReference(4L);
        super.assertEqual(SetUtil.set(2L, 3L), pr.getPrids());
    }

    public void testPublicationURL() {
        PublicationURL purl = new PublicationURL(1L, "mypublicatonurl.com");
        super.assertEqual(1L, purl.getPid());
        super.assertEqual("mypublicatonurl.com", purl.getUrl());
    }

    public void testIndexerEmpty() {
        Indexer idx = new Indexer();
        super.assertEqual(MapUtil.map(), idx.getPublications());
        super.assertEqual(MapUtil.map(), idx.getAuthors());
        super.assertEqual(MapUtil.map(), idx.getAffiliations());
        super.assertEqual(MapUtil.map(), idx.getFieldsofstudy());
        super.assertEqual(MapUtil.map(), idx.getPublicationkeywords());
        super.assertEqual(MapUtil.map(), idx.getPublicationauthoraffiliations());
        super.assertEqual(MapUtil.map(), idx.getPublicationsreferences());
        super.assertEqual(MapUtil.map(), idx.getPublicationurls());
    }

    public void testIndexerAuthors() {
        Indexer idx = new Indexer();
        idx.insertAuthor("autor1", SetUtil.set("cozinhar", "escrever"));
        super.assertEqual("autor1", idx.getAuthorById(1L).getName());
        super.assertEqual(SetUtil.set("cozinhar", "escrever"),
            idx.getAuthorById(1L).getInterests());
        idx.insertAuthor("autor2", SetUtil.set("estudar", "voar"));
        super.assertEqual("autor2", idx.getAuthorById(2L).getName());
        super.assertEqual(SetUtil.set("estudar", "voar"),
            idx.getAuthorById(2L).getInterests());
        idx.addInterestsToAuthor(2L, SetUtil.set("futebol", "praia"));
        idx.addInterestsToAuthor(2L, SetUtil.set("golf"));
        super.assertEqual(SetUtil.set("estudar", "voar", "futebol", "praia",
                "golf"), idx.getAuthorById(2L).getInterests());
        idx.removeInterestsFromAuthor(2L, SetUtil.set("voar"));
        super.assertEqual(SetUtil.set("estudar", "futebol", "praia", "golf"),
            idx.getAuthorById(2L).getInterests());
        idx.removeInterestsFromAuthor(2L,
            SetUtil.set("futebol", "praia", "golf"));
        super.assertEqual(SetUtil.set("estudar"),
            idx.getAuthorById(2L).getInterests());
    }

    public void testCoautherPath() {
        Indexer idx = new Indexer();
        idx.insertAuthor("autor1", SetUtil.set("cozinhar", "escrever"));
        idx.insertAuthor("autor2", SetUtil.set("estudar", "voar"));
        idx.insertAuthor("autor3", SetUtil.set("correr", "cantar"));
        idx.insertAuthor("autor4", SetUtil.set("estudar", "voar"));
        idx.insertAuthor("autor5", SetUtil.set("brincar", "animar"));
        idx.insertAuthor("autor6", SetUtil.set("calcular", "cantar"));
        idx.insertAffiliation("FEUP");
        idx.insertAffiliation("FAC");
        super.assertEqual(2L, MapUtil.dom(idx.getAffiliations()).size());
        idx.insertPublication("A Specifier's Introduction to Formal Methods",
            1990L, 901L, "11.111.11/ISBN", 100L, 1L, SetUtil.set(2L, 3L), 1L,
            SetUtil.set());
        idx.insertPublication("A Specifier's Introduction to Informal Methods",
            1991L, 902L, "11.111.12/ISBN", 10L, 1L, SetUtil.set(3L, 4L), 2L,
            SetUtil.set(1L));
        idx.insertPublication("A Specifier's Introduction to Informal Math",
            1993L, 903L, "11.111.13/ISBN", 13L, 1L, SetUtil.set(5L, 6L, 2L),
            2L, SetUtil.set(1L, 2L));
        super.assertEqual(3L, MapUtil.dom(idx.getPublications()).size());
        super.assertEqual(3L, idx.getPublicationsByAuthor(1L).size());
        super.assertEqual(7L, idx.getCoauthorPath(1L).size());
    }

    public void testCountSelfCitations() {
        Indexer idx = new Indexer();
        idx.insertAuthor("autor1", SetUtil.set("cozinhar", "escrever"));
        idx.insertAffiliation("FEUP");
        idx.insertAffiliation("FAC");
        idx.insertPublication("A Specifier's Introduction to Formal Methods",
            1990L, 901L, "11.111.11/ISBN", 100L, 1L, SetUtil.set(), 1L,
            SetUtil.set());
        idx.insertPublication("A Specifier's Introduction to Informal Methods",
            1991L, 902L, "11.111.12/ISBN", 10L, 1L, SetUtil.set(), 2L,
            SetUtil.set(1L));
        idx.insertPublication("A Specifier's Introduction to Informal Math",
            1993L, 903L, "11.111.13/ISBN", 13L, 1L, SetUtil.set(), 2L,
            SetUtil.set(1L, 2L));
        super.assertEqual(2L,
            idx.getReferencesFromAuthorPublications(idx.getPublicationsByAuthor(
                    1L)).size());
    }

    public void testCountOthersCitations() {
        Indexer idx = new Indexer();
        idx.insertAuthor("autor1", SetUtil.set("cozinhar", "escrever"));
        idx.insertAuthor("autor2", SetUtil.set("estudar", "voar"));
        idx.insertAuthor("autor3", SetUtil.set("correr", "cantar"));
        idx.insertAffiliation("FEUP");
        idx.insertAffiliation("FAC");
        idx.insertPublication("A Specifier's Introduction to Formal Methods",
            1990L, 901L, "11.111.11/ISBN", 100L, 1L, SetUtil.set(), 1L,
            SetUtil.set());
        idx.insertPublication("A Specifier's Introduction to Informal Methods",
            1991L, 902L, "11.111.12/ISBN", 10L, 2L, SetUtil.set(), 2L,
            SetUtil.set(1L));
        idx.insertPublication("A Specifier's Introduction to Informal Math",
            1993L, 903L, "11.111.13/ISBN", 13L, 3L, SetUtil.set(), 2L,
            SetUtil.set(1L, 2L));
        super.assertEqual(1L, idx.getTimesCited(2L));
        super.assertEqual(2L, idx.getTimesCited(1L));
    }

    public void testNumberErdosAuthor() {
        Indexer idx = new Indexer();
        idx.insertAuthor("autor1", SetUtil.set("cozinhar", "escrever"));
        idx.insertAuthor("autor2", SetUtil.set("estudar", "voar"));
        idx.insertAuthor("autor3", SetUtil.set("correr", "cantar"));
        ((Author) Utils.get(idx.getAuthors(), 1L)).setErdos(1L);
        super.assertEqual(1L,
            ((Author) Utils.get(idx.getAuthors(), 1L)).getErdos());
        ((Author) Utils.get(idx.getAuthors(), 2L)).setErdos(100L);
        ((Author) Utils.get(idx.getAuthors(), 3L)).setErdos(3000L);
        idx.insertAffiliation("FEUP");
        idx.insertPublication("A Specifier's Introduction to Formal Methods",
            1990L, 901L, "11.111.11/ISBN", 100L, 1L, SetUtil.set(2L, 3L), 1L,
            SetUtil.set());
        super.assertEqual(2L,
            ((Author) Utils.get(idx.getAuthors(), 2L)).getErdos());
        super.assertEqual(2L,
            ((Author) Utils.get(idx.getAuthors(), 3L)).getErdos());
    }

    public void testAll() {
        testAffiliation();
        testAffiliationChangeName();
        testAuthor();
        testAuthorChangeName();
        testAddInterestsAuthor();
        testRemoveInterestsAuthor();
        testFieldOfStudy();
        testPublication();
        testRaiseRankBy();
        testDecreaseRankBy();
        testPublicationAuthorAffiliation();
        testPublicationAuthorAffiliationChangeName();
        testPublicationKeyword();
        testPublicationKeyword();
        testPublicationReferences();
        testPublicationReferencesAddReference();
        testPublicationReferencesRemoveReference();
        testPublicationURL();
        testIndexerEmpty();
        testIndexerAuthors();
        testCoautherPath();
        testCountSelfCitations();
        testCountOthersCitations();
        testNumberErdosAuthor();
    }

    public String toString() {
        return "TestIndexer{}";
    }
}
