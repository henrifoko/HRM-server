package com.frsummit.HRM.service_impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.frsummit.HRM.configuration.LeaveConfiguration;
import com.frsummit.HRM.crud_repository.LeaveRepository;
import com.frsummit.HRM.model.HRRecord;
import com.frsummit.HRM.model.Leaves;
import com.frsummit.HRM.model.Message;
import com.frsummit.HRM.model.Role;
import com.frsummit.HRM.model.User;
import com.frsummit.HRM.service.HRRecordService;
import com.frsummit.HRM.service.LeaveService;
import com.frsummit.HRM.service.MessageService;
import com.frsummit.HRM.service.RoleService;
import com.frsummit.HRM.service.UserService;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;

@Service("leaveService")
@Transactional
public class LeaveServiceImpl implements LeaveService {
	public static final String ROOT_FILE_DIR = "C:/HRM/server/files/";

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private LeaveRepository leaveRepository;

	@Autowired
	private HRRecordService hrRecordService;

	@Autowired
	private UserService userService;

	@Autowired
	private LeaveService leaveService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MessageService messageService;

/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////// Application Properties //////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
    @Value("${spring.queries.leaves-list}") //Load * from leaves
    private String leavesListQuery;

    @Value("${spring.queries.my-leaves-list}")  //For every User separately
    private String leavesMyListQuery;

    @Value("${spring.queries.all-leaves-by-role}")  // find by  applyToWhom
    private String leavesByRole;

    @Value("${spring.query.leave-status-pending}")  // find by  status = Pending
    private String leaveStatusPending;

    @Value("${spring.query.leave-modify-to-whom}")  // find by  modifyToWhom
    private String leaveModifyToWhom;

/////////////////////////////////////////////////////////////////////////////////////////////////
/////////////////////////////// Services Start //////////////////////////////////////////////////
/////////////////////////////////////////////////////////////////////////////////////////////////
    @Override
    public void saveLeave(Leaves leaves) {
        leaveRepository.save(leaves);
    }

    @Override
    @Modifying
    public void updateLeave(String leaveId, String leaveStatus) {
        Query query = entityManager.createQuery("UPDATE Leaves l SET l.leaveStatus = '" + leaveStatus + "' WHERE l.id='" + leaveId +"'");
        query.executeUpdate();
    }

    //////////////////////////////////////
/////    Admin Section    ////////////
//////////////////////////////////////
    @Override
    public List<Leaves> findAllLeaves() {
        return entityManager.createQuery(leavesListQuery, Leaves.class).getResultList();
    }

    @Override
    public List<Leaves> findAllLeavesByRole(String userRole) {
        return entityManager.createQuery(leavesByRole + userRole + "'" + leaveStatusPending + leaveModifyToWhom + userRole + "'", Leaves.class).getResultList();
    }

    @Override
    public List<Leaves> findAllRecentLeavesByRole(String userRole) {
        return null;
    }

    @Override
    @Modifying
    public void updateLeaveStatus(String leaveId, String selectStatus, String leaveActionBy, String modifyTo) {
        Query query = entityManager.createQuery("UPDATE Leaves l SET l.leaveStatus = '" + selectStatus + "', l.leaveActionBy = '" + leaveActionBy + "', l.modifyToWhom = '" + modifyTo + "' WHERE l.id='" + leaveId +"'");
        query.executeUpdate();
        System.out.println(leaveId + " " + selectStatus + " " + leaveActionBy);
    }

    @Override
    public List<Leaves> findLeavesByLeaveId(int id) {
        return entityManager.createQuery("SELECT l FROM Leaves AS l WHERE l.id='" + id +"'", Leaves.class).getResultList();
    }

    @Override
    public List<Leaves> findLeavesByUserId(String userId) {
        return entityManager.createQuery("SELECT l FROM Leaves AS l WHERE l.userId='" + userId +"' and l.leaveStatus='" + "GRANTED" + "'", Leaves.class).getResultList();
    }

    @Override
    public List<Leaves> findAllLeavesByUserId(String userId) {
        return entityManager.createQuery("SELECT l FROM Leaves AS l WHERE l.userId='" + userId + "'", Leaves.class).getResultList();
    }

    ////////////////////////////////////
////    User Section    ////////////
////////////////////////////////////
    @Override
    public List<Leaves> findMyAllLeaves(String userId) {
        return entityManager.createQuery(leavesMyListQuery + userId + "'", Leaves.class).getResultList();
    }

    @Override
    @Modifying
    public void cancelLeave(int id) {
        System.out.println("3");

//        TypedQuery<User> query = entityManager.createQuery("UPDATE User u SET u.name = :name WHERE u.email='" + eml.getEmail() +"'", User.class);
        Query query = entityManager.createQuery("UPDATE Leaves l SET l.cancellationLeaveStatus= 'True' WHERE l.id='" + id +"'");
        //query.setParameter("email", email);
        query.executeUpdate();
        System.out.println("4");
    }

	@Override
	public byte[] printIndividualReport(User user) {
		// =================== GENERATE THE fieldPath ===================
		String filePath = ROOT_FILE_DIR + user.getId() + "_" + new java.util.Date().toString().replaceAll(":", " ")
				+ ".pdf";

		try {
			String[] months = { "jan", "feb", "mar", "apr", "may", "jun", "jul", "aug", "sep", "oct", "nov", "dec" };
			String[] days = { "one", "two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "eleven",
					"twelve", "thirteen", "fourteen", "fifteen", "sixteen", "seventeen", "eighteen", "nineteen",
					"twenty", "twentyone", "twentytwo", "twentythree", "twentyfour", "twentyfive", "twentysix",
					"twentyseven", "twentyeight", "twentynine", "thirty", "thirtyone" };

			List<Leaves> leavesList = this.findLeavesByUserId(user.getId());

			Document document = new Document(PageSize.A4.rotate(), 0, 0, 0, 0);
			PdfWriter.getInstance(document, new FileOutputStream(filePath));
//        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
//        document.open();
//        document.add(new Paragraph("A Hello World PDF document."));
//        document.close();
//        writer.close();
			document.open();

			Font headerFont = new Font(Font.FontFamily.TIMES_ROMAN, 20.0f, Font.BOLD, BaseColor.BLACK);
			Paragraph header = new Paragraph("Leave Record Card", headerFont);
			header.setAlignment(Element.ALIGN_CENTER);
			document.add(header);

			Font headerUserFont = new Font(Font.FontFamily.TIMES_ROMAN, 15.0f, Font.NORMAL, BaseColor.BLACK);
			Paragraph headerUser = new Paragraph("Year " + String.valueOf(LocalDateTime.now().getYear()),
					headerUserFont);
			headerUser.setAlignment(Element.ALIGN_CENTER);
			document.add(headerUser);

///////////////////////////////////////////////////////////////////////////////////////////////////////////////
//            Employee Details
///////////////////////////////////////////////////////////////////////////////////////////////////////////////

			Font generalHeaderFont = new Font(Font.FontFamily.TIMES_ROMAN, 20.0f, Font.BOLD, BaseColor.BLACK);
			Paragraph empDetailsHeader = new Paragraph("Employer Details", generalHeaderFont);
			document.add(empDetailsHeader);

			Font generalFont = new Font(Font.FontFamily.TIMES_ROMAN, 14.0f, Font.NORMAL, BaseColor.BLACK);

			Paragraph empId = new Paragraph(
					"Employer ID               : " + user.getId(), generalFont);
			document.add(empId);

			Paragraph empName = new Paragraph(
					"Employer Name          : " + user.getFirstName() + user.getMiddleName() + user.getLastName(),
					generalFont);
			document.add(empName);

			Paragraph empDept = new Paragraph(
					"Employer Department : " + user.getDepartment(), generalFont);
			document.add(empDept);

			Paragraph empDesg = new Paragraph(
					"Employer Designation : " + user.getDesignation(), generalFont);
			document.add(empDesg);

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
//        Chart
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

			Paragraph leaveCard = new Paragraph("\nLeave Card", generalFont);
			leaveCard.setAlignment(Element.ALIGN_CENTER);
			document.add(leaveCard);

			PdfPTable table = new PdfPTable(32);
			table.setWidthPercentage(100.0f);
			table.setWidths(new float[] { 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f,
					0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f, 0.3f,
					0.3f, 0.3f, 0.3f });
			table.setSpacingBefore(10);

			// define font for table header row
			Font font = FontFactory.getFont(FontFactory.HELVETICA);
			font.setColor(BaseColor.BLACK);

			// define table header cell
			PdfPCell cell = new PdfPCell();
			cell.setBackgroundColor(BaseColor.WHITE);
			cell.setPadding(1);

//        Create The date number
			for (int i = 0; i <= 31; i++) {
				if (i == 0) {
					cell.setPhrase(new Phrase(" ", font));
					table.addCell(cell);
				} else {
					cell.setPhrase(new Phrase(String.valueOf(i), font));
					table.addCell(cell);
				}
			}

			List<String> applyFromList = new ArrayList<>();
			List<String> applyToList = new ArrayList<>();
			if (leavesList.size() != 0) {
				for (int i = 0; i < leavesList.size(); i++) {
					Leaves leaves = leavesList.get(i);
					String applyFrom = leaves.getLeaveApplyFrom().toString();
					String applyTo = leaves.getLeaveApplyTo().toString();

					String[] partsApplyFrom = applyFrom.split(" ");
					String[] partsApplyTo = applyTo.split(" ");

					applyFromList.add(partsApplyFrom[0]);
					applyToList.add(partsApplyTo[0]);
				}
			}

			// System.out.println("////////////" + applyFromList.get(0) + " " +
			// applyToList.get(0));
			// System.out.println("////////////" + applyFromList.get(1) + " " +
			// applyToList.get(1));

//        Set the month name
			for (int i = 0; i < 12; i++) {
				for (int j = 0; j <= 31; j++) {
					if (j == 0) {
						cell.setPhrase(new Phrase(months[i], font));
						table.addCell(cell);
					} else {

						for (int k = 0; k < applyFromList.size(); k++) {
							String[] applyFromParts = applyFromList.get(k).split("-");
							String[] applyToParts = applyToList.get(k).split("-");

							String applyFromYear = applyFromParts[0];
							String applyFromMonth = applyFromParts[1];
							String applyFromDay = applyFromParts[2];

							String applyToYear = applyToParts[0];
							String applyToMonth = applyToParts[1];
							String applyToDay = applyToParts[2];

//                        if(applyFromYear.equalsIgnoreCase(String.valueOf(LocalDateTime.now().getYear()))
//                                && applyToYear.equalsIgnoreCase(String.valueOf(LocalDateTime.now().getYear()))){
//                            if(applyFromMonth <= String.valueOf(j))
//                        }
						}

						cell.setPhrase(new Phrase("Y", font));
						table.addCell(cell);
					}
				}
			}

			document.add(table);

			Paragraph newLine = new Paragraph("\n\n\n", generalFont);
			document.add(newLine);

			Chunk glue = new Chunk(new VerticalPositionMark());
			Paragraph p = new Paragraph(".........................");
			p.add(new Chunk(glue));
			p.add(".........................");
			p.add(new Chunk(glue));
			p.add(".........................");
			document.add(p);

//            Chunk glue2 = new Chunk(new VerticalPositionMark());
//            Paragraph p2 = new Paragraph(myAuthorization.userFullName());
//            p2.add(new Chunk(glue2));
//            p2.add("Supervisor");
//            p2.add(new Chunk(glue2));
//            p2.add("Chairman");
//            document.add(p2);

			document.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// =============== Lecture du document ===============
		FileInputStream fis = null;
		FileChannel fc = null;

		try {
			fis = new FileInputStream(new File(filePath));
			fc = fis.getChannel();
			int size = (int) fc.size();

			ByteBuffer bBuff = ByteBuffer.allocate(size);
			fc.read(bBuff);
			bBuff.flip();
			byte[] tabByte = bBuff.array();

			return tabByte;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fis != null)
					fis.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return null;
	}

	@Override
	public List<HRRecord> getLeaveReportConsolidated() {
		return this.hrRecordService.findAllHRRecords();
	}

	@Override
	public List<HRRecord> getLeaveReportConsolidatedSelectedByDate(String department, String dateFrom, String dateTo) {

		List<User> userList = userService.findUserByDepartment(department);
		List<HRRecord> hrRecordList = hrRecordService.findAllHRRecords();
		List<Leaves> leavesList = leaveService.findAllLeaves();
//        System.out.println(leavesList);

//        List<String> userIdList = new ArrayList<>();
//        System.out.println("this :" + userList);
//        User user;
//        System.out.println(userList.size());
//        for(int i=0; i<userList.size()-1; i++){
//            user = userList.get(i);
//            userIdList.add(user.getId());
//        }
//        System.out.println("This");
//        System.out.println(userIdList);
//        System.out.println(userIdList.size());

		if (userList.size() != 0 && hrRecordList.size() != 0 && leavesList.size() != 0) {
//          Put value from database to array list
			List<User> userList1 = new ArrayList<>();
			List<HRRecord> hrRecordList1 = new ArrayList<>();
			for (int i = 0; i < hrRecordList.size() - 1; i++) {
				User user1 = userList.get(i);
				userList1.add(user1);

				List<HRRecord> hrByUserId = hrRecordService.getAllRecord(user1.getId());
				HRRecord hrRecordById = hrByUserId.get(0);
				hrRecordList1.add(hrRecordById);

//                HRRecord hrRecord = hrRecordList.get(i);
//                hrRecordList1.add(hrRecord);
			}

			List<Leaves> leavesList1 = new ArrayList<>();
			for (int i = 0; i < leavesList.size(); i++) {
				Leaves leaves = leavesList.get(i);
				leavesList1.add(leaves);
			}

//        Put userId from ArrayList to new userId ArrayList
			List<String> userId = new ArrayList<>();
			List<String> hrUserId = new ArrayList<>();
			for (int i = 0; i < hrRecordList1.size(); i++) {
				userId.add(userList1.get(i).getId());
				hrUserId.add(hrRecordList1.get(i).getUserId());
			}

			System.out.println(userId.get(0) + " " + hrUserId.get(0));

			List<String> leaveUserId = new ArrayList<>();
			for (int i = 0; i < leavesList1.size(); i++) {
				leaveUserId.add(leavesList1.get(i).getUserId());
			}

//        Select specific hr record from same userId
			List<HRRecord> hrRecordList2 = new ArrayList<>();
			for (int i = 0; i < userId.size(); i++) {
				for (int j = 0; j <= hrUserId.size() - 1; j++) {
					if (userId.get(i).equalsIgnoreCase(hrUserId.get(j))) {
//                        System.out.println(userId.get(i) + " " + hrUserId.get(j));
						String id = hrUserId.get(j);
						hrRecordList2.add(hrRecordList1.get(j));
					}
				}
			}

			String dateFrm = dateFrom.toString();
			String dateT = dateTo.toString();
			String frmParts1 = dateFrom + " 00:00:00.0";
			String toParts1 = dateTo + " 00:00:00.0";
			String[] frmParts2 = dateFrm.split("-");
			String[] toParts2 = dateT.split("-");

			List<Leaves> finalLeaveList = new ArrayList<>();

			// if(dateFrm.length() > 0 && dateT.length() > 0){
			for (int i = 0; i < leavesList1.size(); i++) {
				Leaves leaves = leavesList1.get(i);

				String levDatFrm = leaves.getLeaveApplyFrom().toString();
				String levDatTo = leaves.getLeaveApplyTo().toString();
				String[] levfrmParts1 = levDatFrm.split(" ");
				String[] levtoParts1 = levDatTo.split(" ");
				String[] levfrmParts2 = levfrmParts1[0].toString().split("-");
				String[] levtoParts2 = levtoParts1[0].toString().split("-");

				if (Integer.parseInt(levfrmParts2[0]) >= Integer.parseInt(frmParts2[0])
						&& Integer.parseInt(levtoParts2[0]) <= Integer.parseInt(toParts2[0])) {
					if (Integer.parseInt(levfrmParts2[1]) >= Integer.parseInt(frmParts2[1])
							&& Integer.parseInt(levtoParts2[1]) <= Integer.parseInt(toParts2[1])) {
						if (Integer.parseInt(levfrmParts2[2]) >= Integer.parseInt(frmParts2[2])
								&& Integer.parseInt(levtoParts2[2]) <= Integer.parseInt(toParts2[2])) {
							finalLeaveList.add(leaves);
						}
					}
				}
			}
			// }

			List<String> ids = new ArrayList<>();
			for (int i = 0; i < finalLeaveList.size(); i++) {
				ids.add(finalLeaveList.get(i).getUserId());
			}

//        Select specific hr record from same userId after filter leaves
			List<HRRecord> hrRecordList3 = new ArrayList<>();
			for (int i = 0; i < ids.size() - 1; i++) {
				for (int j = 0; j <= hrRecordList2.size() - 1; j++) {
					if (ids.get(i).equalsIgnoreCase(hrRecordList2.get(j).getUserId())) {
//                        System.out.println(userId.get(i) + " " + hrUserId.get(j));
						String id = hrUserId.get(j);
						hrRecordList3.add(hrRecordList2.get(j));
					}
				}
			}

			return hrRecordList3;
		}

		return null;
	}

	@Override
	public void leaveStatusUpdate(String userRole, String leaveId, String selectStatus, String message) {
		System.out.println(leaveId + " " + selectStatus + " " + message);

		String leaveActionBy = userRole;
		String modifyTo;

		LeaveConfiguration leaveConfig = new LeaveConfiguration();
		List<Role> roleList = roleService.findAllRole(userRole);
		Role role = roleList.get(0);
		modifyTo = leaveConfig.mapForNextRole(leaveActionBy, role.getRoleChain());

		if (!selectStatus.equalsIgnoreCase("Granted")
				|| userRole.equalsIgnoreCase("ADMIN"))
			modifyTo = "Modified";
		System.out.println("This is leave id : " + leaveId);

		if (selectStatus.equalsIgnoreCase("Checking")) {
			Message m = new Message(leaveId, message, "UnCheck");
			System.out.println("This is message : " + m);
			messageService.saveMessage(m);
			leaveService.updateLeaveStatus(leaveId, selectStatus, leaveActionBy,
					userRole);
		} else {
			leaveService.updateLeaveStatus(leaveId, selectStatus, leaveActionBy, modifyTo);
			List<Role> roleList2 = roleService.findAllRoles();
			Role parentRole = roleList2.get(1);
			int leaveIdInt = Integer.parseInt(leaveId);
			List<Leaves> leavesList = leaveService.findLeavesByLeaveId(leaveIdInt);
			Leaves leaves = leavesList.get(0);
			System.out.println(leaves.getLeaveType());

			List<HRRecord> hrRecordList = hrRecordService.getAllRecord(leaves.getUserId());
			if (hrRecordList.size() != 0) {
				HRRecord hrRecord = hrRecordList.get(0);
				if (userRole.equalsIgnoreCase(parentRole.getRole())) {
					if (leaves.getLeaveType().equalsIgnoreCase("Personal")) {
						int taken = leaves.getTotalDayOfLeave() + hrRecord.getTotalLeaveTakenPersonal();
						int balance = hrRecord.getLeaveBalancePersonal() - leaves.getTotalDayOfLeave();
						hrRecordService.updateHRRecord(leaves.getUserId(), "Personal", taken, balance);
					} else if (leaves.getLeaveType().equalsIgnoreCase("Sick")) {
						int taken = leaves.getTotalDayOfLeave() + hrRecord.getTotalLeaveTakenPersonal();
						int balance = hrRecord.getLeaveBalancePersonal() - leaves.getTotalDayOfLeave();
						hrRecordService.updateHRRecord(leaves.getUserId(), "Sick", taken, balance);
					} else if (leaves.getLeaveType().equalsIgnoreCase("Planned")) {
						int taken = leaves.getTotalDayOfLeave() + hrRecord.getTotalLeaveTakenPersonal();
						int balance = hrRecord.getLeaveBalancePersonal() - leaves.getTotalDayOfLeave();
						hrRecordService.updateHRRecord(leaves.getUserId(), "Planned", taken, balance);
					} else if (leaves.getLeaveType().equalsIgnoreCase("Vacation")) {
						int taken = leaves.getTotalDayOfLeave() + hrRecord.getTotalLeaveTakenPersonal();
						int balance = hrRecord.getLeaveBalancePersonal() - leaves.getTotalDayOfLeave();
						hrRecordService.updateHRRecord(leaves.getUserId(), "Vacation", taken, balance);
					} else if (leaves.getLeaveType().equalsIgnoreCase("Maternity")) {
						int taken = leaves.getTotalDayOfLeave() + hrRecord.getTotalLeaveTakenPersonal();
						int balance = hrRecord.getLeaveBalancePersonal() - leaves.getTotalDayOfLeave();
						hrRecordService.updateHRRecord(leaves.getUserId(), "Maternity", taken, balance);
					} else if (leaves.getLeaveType().equalsIgnoreCase("Other")) {
						int taken = leaves.getTotalDayOfLeave() + hrRecord.getTotalLeaveTakenPersonal();
						int balance = hrRecord.getLeaveBalancePersonal() - leaves.getTotalDayOfLeave();
						hrRecordService.updateHRRecord(leaves.getUserId(), "Other", taken, balance);
					}
				}
			}
		}
	}
}
