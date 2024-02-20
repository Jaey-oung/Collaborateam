package com.collaborateam.www.service;

import com.collaborateam.www.domain.*;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Calendar;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/root-context.xml"})
public class DemoSettingTest {
    @Autowired
    UserService userService;
    @Autowired
    FieldService fieldService;
    @Autowired
    SpecService specService;
    @Autowired
    BoardService boardService;
    @Autowired
    CommentService commentService;
    @Autowired
    TeamService teamService;
    @Autowired
    MemberService memberService;
    @Autowired
    InviteService inviteService;
    @Autowired
    TaskService taskService;
    @Autowired
    GoalService goalService;

    UserDto userDto1;
    UserDto userDto2;
    Calendar birth;

    FieldDto fieldDto1;
    FieldDto fieldDto2;
    FieldDto fieldDto3;
    FieldDto fieldDto4;
    FieldDto fieldDto5;
    FieldDto fieldDto6;
    FieldDto fieldDto7;
    FieldDto fieldDto8;
    FieldDto fieldDto9;
    FieldDto fieldDto10;
    FieldDto fieldDto11;
    FieldDto fieldDto12;
    FieldDto fieldDto13;
    FieldDto fieldDto14;
    FieldDto fieldDto15;
    FieldDto fieldDto16;
    FieldDto fieldDto17;
    FieldDto fieldDto18;
    FieldDto fieldDto19;
    FieldDto fieldDto20;
    FieldDto fieldDto21;
    FieldDto fieldDto22;

    SpecDto specDto1;
    SpecDto specDto2;
    SpecDto specDto3;
    SpecDto specDto4;
    SpecDto specDto5;
    SpecDto specDto6;
    SpecDto specDto7;
    SpecDto specDto8;
    SpecDto specDto9;
    SpecDto specDto10;
    SpecDto specDto11;
    SpecDto specDto12;
    SpecDto specDto13;
    SpecDto specDto14;
    SpecDto specDto15;
    SpecDto specDto16;
    SpecDto specDto17;
    SpecDto specDto18;
    SpecDto specDto19;
    SpecDto specDto20;
    SpecDto specDto21;
    SpecDto specDto22;
    SpecDto specDto23;
    SpecDto specDto24;
    SpecDto specDto25;
    SpecDto specDto26;
    SpecDto specDto27;
    SpecDto specDto28;
    SpecDto specDto29;
    SpecDto specDto30;
    SpecDto specDto31;
    SpecDto specDto32;
    SpecDto specDto33;
    SpecDto specDto34;
    SpecDto specDto35;
    SpecDto specDto36;
    SpecDto specDto37;
    SpecDto specDto38;
    SpecDto specDto39;
    SpecDto specDto40;
    SpecDto specDto41;
    SpecDto specDto42;
    SpecDto specDto43;

    Integer bno;
    BoardDto sampleBoard;
    BoardDto boardDto1;
    BoardDto boardDto2;
    BoardDto boardDto3;
    BoardDto boardDto4;
    BoardDto boardDto5;
    BoardDto boardDto6;
    BoardDto boardDto7;
    BoardDto boardDto8;
    BoardDto boardDto9;
    BoardDto boardDto10;
    BoardDto boardDto11;
    BoardDto boardDto12;
    BoardDto boardDto13;
    BoardDto boardDto14;
    BoardDto boardDto15;
    BoardDto boardDto16;
    BoardDto boardDto17;
    BoardDto boardDto18;
    BoardDto boardDto19;
    BoardDto boardDto20;
    BoardDto boardDto21;
    BoardDto boardDto22;
    BoardDto boardDto23;
    BoardDto boardDto24;
    BoardDto boardDto25;
    BoardDto boardDto26;
    BoardDto boardDto27;
    BoardDto boardDto28;
    BoardDto boardDto29;
    BoardDto boardDto30;
    BoardDto boardDto31;
    BoardDto boardDto32;
    BoardDto boardDto33;
    BoardDto boardDto34;
    BoardDto boardDto35;
    BoardDto boardDto36;
    BoardDto boardDto37;
    BoardDto boardDto38;
    BoardDto boardDto39;
    BoardDto boardDto40;
    BoardDto boardDto41;
    BoardDto boardDto42;
    BoardDto boardDto43;

    CommentDto commentDto1;
    CommentDto commentDto2;

    Integer tno;
    TeamDto teamDto1;
    TeamDto teamDto2;
    TeamDto teamDto3;
    TeamDto teamDto4;
    TeamDto teamDto5;
    TeamDto teamDto6;
    TeamDto teamDto7;
    TeamDto teamDto8;

    InviteDto inviteDto1;
    InviteDto inviteDto2;
    InviteDto inviteDto3;
    InviteDto inviteDto4;

    MemberDto memberDto1;

    TaskDto taskDto1;
    TaskDto taskDto2;

    GoalDto goalDto1;
    GoalDto goalDto2;

    @Before
    public void init() throws Exception {
        birth = Calendar.getInstance();
        birth.clear();
        birth.set(2000, Calendar.DECEMBER, 19);

        // Since the users are manually created, password encryption should be disabled
        userDto1 = new UserDto("leader1", "leader1!", "leader1@leader1.com", "leader1", birth.getTime());
        userDto2 = new UserDto("member1", "member1!", "member1@member1.com", "member1", birth.getTime());

        fieldDto1 = new FieldDto("A", "All");
        fieldDto2 = new FieldDto("IT", "Information Technology");
        fieldDto3 = new FieldDto("FIN", "Finance");
        fieldDto4 = new FieldDto("EDU", "Education");
        fieldDto5 = new FieldDto("ENG", "Engineering");
        fieldDto6 = new FieldDto("MAR", "Marketing");
        fieldDto7 = new FieldDto("DES", "Design");
        fieldDto8 = new FieldDto("SCI", "Science");
        fieldDto9 = new FieldDto("MED", "Medical");
        fieldDto10 = new FieldDto("LAW", "Law");
        fieldDto11 = new FieldDto("HR", "Human Resources");
        fieldDto12 = new FieldDto("GOV", "Government & Public Service");
        fieldDto13 = new FieldDto("ART", "Arts");
        fieldDto14 = new FieldDto("CON", "Construction");
        fieldDto15 = new FieldDto("ENT", "Entertainment");
        fieldDto16 = new FieldDto("HOS", "Hospitality");
        fieldDto17 = new FieldDto("MAN", "Manufacturing");
        fieldDto18 = new FieldDto("RET", "Retail");
        fieldDto19 = new FieldDto("TRA", "Transportation");
        fieldDto20 = new FieldDto("AGR", "Agriculture");
        fieldDto21 = new FieldDto("ENV", "Environmental");
        fieldDto22 = new FieldDto("PHA", "Pharmaceutical");

        specDto1 = new SpecDto("A", "All", "A");
        specDto2 = new SpecDto("WDEV", "Web Development", "IT");
        specDto3 = new SpecDto("SDEV", "Software Development", "IT");
        specDto4 = new SpecDto("FANA", "Financial Analysis", "FIN");
        specDto5 = new SpecDto("RMAN", "Risk Management", "FIN");
        specDto6 = new SpecDto("TEA", "Teaching", "EDU");
        specDto7 = new SpecDto("EADM", "Educational Administration", "EDU");
        specDto8 = new SpecDto("MENG", "Mechanical Engineering", "ENG");
        specDto9 = new SpecDto("EENG", "Electrical Engineering", "ENG");
        specDto10 = new SpecDto("DMAR", "Digital Marketing", "MAR");
        specDto11 = new SpecDto("SMMAR", "Social Media Marketing", "MAR");
        specDto12 = new SpecDto("GDES", "Graphic Design", "DES");
        specDto13 = new SpecDto("UXIDES", "UX/UI Design", "DES");
        specDto14 = new SpecDto("LSCI", "Life Sciences", "SCI");
        specDto15 = new SpecDto("PSCI", "Physical Sciences", "SCI");
        specDto16 = new SpecDto("MED", "Medicine", "MED");
        specDto17 = new SpecDto("NUR", "Nursing", "MED");
        specDto18 = new SpecDto("CLAW", "Corporate Law", "LAW");
        specDto19 = new SpecDto("IPLAW", "Intellectual Property Law", "LAW");
        specDto20 = new SpecDto("REC", "Recruitment", "HR");
        specDto21 = new SpecDto("TDEV", "Training and Development", "HR");
        specDto22 = new SpecDto("PADM", "Public Administration", "GOV");
        specDto23 = new SpecDto("PDEV", "Policy Development", "GOV");
        specDto24 = new SpecDto("PART", "Performing Arts", "ART");
        specDto25 = new SpecDto("VART", "Visual Arts", "ART");
        specDto26 = new SpecDto("CMAN", "Construction Management", "CON");
        specDto27 = new SpecDto("CENG", "Civil Engineering", "CON");
        specDto28 = new SpecDto("FIL", "Film", "ENT");
        specDto29 = new SpecDto("MUS", "Music", "ENT");
        specDto30 = new SpecDto("HMAN", "Hotel Management", "HOS");
        specDto31 = new SpecDto("RSER", "Restaurant Service", "HOS");
        specDto32 = new SpecDto("PMAN", "Production Management", "MAN");
        specDto33 = new SpecDto("QMAN", "Quality Management", "MAN");
        specDto34 = new SpecDto("SMAN", "Store Management", "RET");
        specDto35 = new SpecDto("BMER", "Buyer Merchandising", "RET");
        specDto36 = new SpecDto("LMAN", "Logistics Management", "TRA");
        specDto37 = new SpecDto("AVI", "Aviation", "TRA");
        specDto38 = new SpecDto("AFAR", "Agronomy and Farming", "AGR");
        specDto39 = new SpecDto("AHOR", "Agri-Business/ Horticulture", "AGR");
        specDto40 = new SpecDto("EMAN", "Environmental Management", "ENV");
        specDto41 = new SpecDto("ECO", "Ecology", "ENV");
        specDto42 = new SpecDto("PDEV", "Pharmaceutical Development", "PHA");
        specDto43 = new SpecDto("PHA", "Pharmacology", "PHA");

        sampleBoard = new BoardDto(fieldDto1.getValue(), specDto1.getValue(), "Sample Board", "Sample Content", userDto2.getName());
        boardDto1 = new BoardDto(fieldDto1.getValue(), specDto1.getValue(), "Board1", "Content1", userDto1.getName());
        boardDto2 = new BoardDto(fieldDto2.getValue(), specDto2.getValue(), "Board2", "Content2", userDto1.getName());
        boardDto3 = new BoardDto(fieldDto2.getValue(), specDto3.getValue(), "Board3", "Content3", userDto1.getName());
        boardDto4 = new BoardDto(fieldDto3.getValue(), specDto4.getValue(), "Board4", "Content4", userDto1.getName());
        boardDto5 = new BoardDto(fieldDto3.getValue(), specDto5.getValue(), "Board5", "Content5", userDto1.getName());
        boardDto6 = new BoardDto(fieldDto4.getValue(), specDto6.getValue(), "Board6", "Content6", userDto1.getName());
        boardDto7 = new BoardDto(fieldDto4.getValue(), specDto7.getValue(), "Board7", "Content7", userDto1.getName());
        boardDto8 = new BoardDto(fieldDto5.getValue(), specDto8.getValue(), "Board8", "Content8", userDto1.getName());
        boardDto9 = new BoardDto(fieldDto5.getValue(), specDto9.getValue(), "Board9", "Content9", userDto1.getName());
        boardDto10 = new BoardDto(fieldDto6.getValue(), specDto10.getValue(), "Board10", "Content10", userDto1.getName());
        boardDto11 = new BoardDto(fieldDto6.getValue(), specDto11.getValue(), "Board11", "Content11", userDto1.getName());
        boardDto12 = new BoardDto(fieldDto7.getValue(), specDto12.getValue(), "Board12", "Content12", userDto1.getName());
        boardDto13 = new BoardDto(fieldDto7.getValue(), specDto13.getValue(), "Board13", "Content13", userDto1.getName());
        boardDto14 = new BoardDto(fieldDto8.getValue(), specDto14.getValue(), "Board14", "Content14", userDto1.getName());
        boardDto15 = new BoardDto(fieldDto8.getValue(), specDto15.getValue(), "Board15", "Content15", userDto1.getName());
        boardDto16 = new BoardDto(fieldDto9.getValue(), specDto16.getValue(), "Board16", "Content16", userDto1.getName());
        boardDto17 = new BoardDto(fieldDto9.getValue(), specDto17.getValue(), "Board17", "Content17", userDto1.getName());
        boardDto18 = new BoardDto(fieldDto10.getValue(), specDto18.getValue(), "Board18", "Content18", userDto1.getName());
        boardDto19 = new BoardDto(fieldDto10.getValue(), specDto19.getValue(), "Board19", "Content19", userDto1.getName());
        boardDto20 = new BoardDto(fieldDto11.getValue(), specDto20.getValue(), "Board20", "Content20", userDto1.getName());
        boardDto21 = new BoardDto(fieldDto11.getValue(), specDto21.getValue(), "Board21", "Content21", userDto1.getName());
        boardDto22 = new BoardDto(fieldDto12.getValue(), specDto22.getValue(), "Board22", "Content22", userDto1.getName());
        boardDto23 = new BoardDto(fieldDto12.getValue(), specDto23.getValue(), "Board23", "Content23", userDto1.getName());
        boardDto24 = new BoardDto(fieldDto13.getValue(), specDto24.getValue(), "Board24", "Content24", userDto1.getName());
        boardDto25 = new BoardDto(fieldDto13.getValue(), specDto25.getValue(), "Board25", "Content25", userDto1.getName());
        boardDto26 = new BoardDto(fieldDto14.getValue(), specDto26.getValue(), "Board26", "Content26", userDto1.getName());
        boardDto27 = new BoardDto(fieldDto14.getValue(), specDto27.getValue(), "Board27", "Content27", userDto1.getName());
        boardDto28 = new BoardDto(fieldDto15.getValue(), specDto28.getValue(), "Board28", "Content28", userDto1.getName());
        boardDto29 = new BoardDto(fieldDto15.getValue(), specDto29.getValue(), "Board29", "Content29", userDto1.getName());
        boardDto30 = new BoardDto(fieldDto16.getValue(), specDto30.getValue(), "Board30", "Content30", userDto1.getName());
        boardDto31 = new BoardDto(fieldDto16.getValue(), specDto31.getValue(), "Board31", "Content31", userDto1.getName());
        boardDto32 = new BoardDto(fieldDto17.getValue(), specDto32.getValue(), "Board32", "Content32", userDto1.getName());
        boardDto33 = new BoardDto(fieldDto17.getValue(), specDto33.getValue(), "Board33", "Content33", userDto1.getName());
        boardDto34 = new BoardDto(fieldDto18.getValue(), specDto34.getValue(), "Board34", "Content34", userDto1.getName());
        boardDto35 = new BoardDto(fieldDto18.getValue(), specDto35.getValue(), "Board35", "Content35", userDto1.getName());
        boardDto36 = new BoardDto(fieldDto19.getValue(), specDto36.getValue(), "Board36", "Content36", userDto1.getName());
        boardDto37 = new BoardDto(fieldDto19.getValue(), specDto37.getValue(), "Board37", "Content37", userDto1.getName());
        boardDto38 = new BoardDto(fieldDto20.getValue(), specDto38.getValue(), "Board38", "Content38", userDto1.getName());
        boardDto39 = new BoardDto(fieldDto20.getValue(), specDto39.getValue(), "Board39", "Content39", userDto1.getName());
        boardDto40 = new BoardDto(fieldDto21.getValue(), specDto40.getValue(), "Board40", "Content40", userDto1.getName());
        boardDto41 = new BoardDto(fieldDto21.getValue(), specDto41.getValue(), "Board41", "Content41", userDto1.getName());
        boardDto42 = new BoardDto(fieldDto22.getValue(), specDto42.getValue(), "Board42", "Content42", userDto1.getName());
        boardDto43 = new BoardDto(fieldDto22.getValue(), specDto43.getValue(), "Board43", "Content43", userDto1.getName());

        teamDto1 = new TeamDto(userDto1.getId(), "team1", "detail1");
        teamDto2 = new TeamDto(userDto1.getId(), "team2", "detail2");
        teamDto3 = new TeamDto(userDto1.getId(), "team3", "detail3");
        teamDto4 = new TeamDto(userDto1.getId(), "team4", "detail4");
        teamDto5 = new TeamDto(userDto2.getId(), "team5", "detail5");
        teamDto6 = new TeamDto(userDto2.getId(), "team6", "detail6");
        teamDto7 = new TeamDto(userDto2.getId(), "team7", "detail7");
        teamDto8 = new TeamDto(userDto2.getId(), "team8", "detail8");
    }

    @Test
    public void setting() throws Exception {
        userService.deleteAll();
        boardService.deleteAll();
        fieldService.deleteAll();
        specService.deleteAll();
        teamService.deleteAll();
        inviteService.deleteAll();

        // Should delete all comments, goals, members, and tasks
        userService.create(userDto2);
        userService.updateToOldUser();

        userService.create(userDto1);

        fieldService.create(fieldDto1);
        fieldService.create(fieldDto2);
        fieldService.create(fieldDto3);
        fieldService.create(fieldDto4);
        fieldService.create(fieldDto5);
        fieldService.create(fieldDto6);
        fieldService.create(fieldDto7);
        fieldService.create(fieldDto8);
        fieldService.create(fieldDto9);
        fieldService.create(fieldDto10);
        fieldService.create(fieldDto11);
        fieldService.create(fieldDto12);
        fieldService.create(fieldDto13);
        fieldService.create(fieldDto14);
        fieldService.create(fieldDto15);
        fieldService.create(fieldDto16);
        fieldService.create(fieldDto17);
        fieldService.create(fieldDto18);
        fieldService.create(fieldDto19);
        fieldService.create(fieldDto20);
        fieldService.create(fieldDto21);
        fieldService.create(fieldDto22);

        specService.create(specDto1);
        specService.create(specDto2);
        specService.create(specDto3);
        specService.create(specDto4);
        specService.create(specDto5);
        specService.create(specDto6);
        specService.create(specDto7);
        specService.create(specDto8);
        specService.create(specDto9);
        specService.create(specDto10);
        specService.create(specDto11);
        specService.create(specDto12);
        specService.create(specDto13);
        specService.create(specDto14);
        specService.create(specDto15);
        specService.create(specDto16);
        specService.create(specDto17);
        specService.create(specDto18);
        specService.create(specDto19);
        specService.create(specDto20);
        specService.create(specDto21);
        specService.create(specDto22);
        specService.create(specDto23);
        specService.create(specDto24);
        specService.create(specDto25);
        specService.create(specDto26);
        specService.create(specDto27);
        specService.create(specDto28);
        specService.create(specDto29);
        specService.create(specDto30);
        specService.create(specDto31);
        specService.create(specDto32);
        specService.create(specDto33);
        specService.create(specDto34);
        specService.create(specDto35);
        specService.create(specDto36);
        specService.create(specDto37);
        specService.create(specDto38);
        specService.create(specDto39);
        specService.create(specDto40);
        specService.create(specDto41);
        specService.create(specDto42);
        specService.create(specDto43);

        for(int i=0; i<100; i++) {
            boardService.create(sampleBoard);
        }

        boardService.create(boardDto1);
        boardService.create(boardDto2);
        boardService.create(boardDto3);
        boardService.create(boardDto4);
        boardService.create(boardDto5);
        boardService.create(boardDto6);
        boardService.create(boardDto7);
        boardService.create(boardDto8);
        boardService.create(boardDto9);
        boardService.create(boardDto10);
        boardService.create(boardDto11);
        boardService.create(boardDto12);
        boardService.create(boardDto13);
        boardService.create(boardDto14);
        boardService.create(boardDto15);
        boardService.create(boardDto16);
        boardService.create(boardDto17);
        boardService.create(boardDto18);
        boardService.create(boardDto19);
        boardService.create(boardDto20);
        boardService.create(boardDto21);
        boardService.create(boardDto22);
        boardService.create(boardDto23);
        boardService.create(boardDto24);
        boardService.create(boardDto25);
        boardService.create(boardDto26);
        boardService.create(boardDto27);
        boardService.create(boardDto28);
        boardService.create(boardDto29);
        boardService.create(boardDto30);
        boardService.create(boardDto31);
        boardService.create(boardDto32);
        boardService.create(boardDto33);
        boardService.create(boardDto34);
        boardService.create(boardDto35);
        boardService.create(boardDto36);
        boardService.create(boardDto37);
        boardService.create(boardDto38);
        boardService.create(boardDto39);
        boardService.create(boardDto40);
        boardService.create(boardDto41);
        boardService.create(boardDto42);
        boardService.create(boardDto43);

        bno = boardService.getList().get(0).getBno();
        commentDto1 = new CommentDto(bno, null, "comment1", userDto2.getId());

        commentService.create(commentDto1);

        teamService.create(teamDto1);
        teamService.create(teamDto2);
        teamService.create(teamDto3);
        teamService.create(teamDto4);
        teamService.create(teamDto5);
        teamService.create(teamDto6);
        teamService.create(teamDto7);
        teamService.create(teamDto8);

        tno = teamService.getList().get(3).getTno();
        inviteDto1 = new InviteDto(tno, userDto1.getId());

        tno = teamService.getList().get(2).getTno();
        inviteDto2 = new InviteDto(tno, userDto1.getId());

        tno = teamService.getList().get(1).getTno();
        inviteDto3 = new InviteDto(tno, userDto1.getId());

        tno = teamService.getList().get(0).getTno();
        inviteDto4 = new InviteDto(tno, userDto1.getId());

        inviteService.create(inviteDto1);
        inviteService.create(inviteDto2);
        inviteService.create(inviteDto3);
        inviteService.create(inviteDto4);

        tno = teamService.getList().get(7).getTno();

        memberDto1 = new MemberDto(tno, userDto2.getId());
        memberService.create(memberDto1);

        taskDto1 = new TaskDto(tno, userDto1.getId(), "task1");
        taskDto2 = new TaskDto(tno, userDto2.getId(), "task2");

        taskService.create(taskDto1);
        taskService.create(taskDto2);

        goalDto1 = new GoalDto(tno, "goal1");
        goalDto2 = new GoalDto(tno, "goal2");

        goalService.create(goalDto1);
        goalService.create(goalDto2);
    }
}