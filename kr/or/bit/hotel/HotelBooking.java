package kr.or.bit.hotel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HotelBooking {
    private boolean loginCheck; // 로그인 유무
    private Member memberLoggedIn; // 로그인된 회원
    private Hotel hotel;
    private Scanner sc;
    private File file;
    private FileInputStream fis;
    private ObjectInputStream in;
    private FileOutputStream fos;
    private ObjectOutputStream out;
    private Reservation r;

    public HotelBooking() {
        sc = new Scanner(System.in);
    }

    public void run() {
        loginCheck = false;
        memberLoggedIn = null;
        loadHotel();
        printMenu();
    }

    /*
     * 호텔 정보 불러오기
     *
     * 프로그램에서 활용한 호텔의 정보를 불러오는 함수
     *
     * 호텔 정보 파일이 없으면 불러올 호텔 정보가 없어 프로그램 강제 종료
     *
     * 작성자 : 윤종석
     */
    private void loadHotel() {
        file = new File(CustomString.PATH_HOTEL);
        if (!file.exists()) {
            System.out.println("호텔 정보가 존재하지 않습니다.");
            System.out.println("프로그램을 종료합니다.");
            System.exit(0);
        }
        try {
            fis = new FileInputStream(file);
            in = new ObjectInputStream(fis);
            hotel = (Hotel) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
                fis.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /*
     * 호텔 정보 저장하기
     *
     * 변경된 호텔 정보를 저장
     *
     * 폴더가 없으면 자동으로 폴더 생성 후 저장
     *
     * 작성자 : 윤종석
     */
    private void saveHotel() {
        file = new File(CustomString.PATH_DIRECTORY);
        if (!file.exists()) {
            file.mkdirs();
        }
        file = new File(CustomString.PATH_HOTEL);
        try {
            fos = new FileOutputStream(file);
            out = new ObjectOutputStream(fos);
            out.writeObject(hotel);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void getRoomInfo() { // 객실정보보기
        for (int i = 0; i < hotel.getRoomInfos().length; i++) {
            String kitchen;
            if (hotel.getRoomInfos()[i].isKitchen()) {
                kitchen = "유";
            } else {
                kitchen = "무";
            }
            System.out.println("  [방이름] : " + hotel.getRoomInfos()[i].getRoomName() + "룸  " + "  [가격] : "
                    + hotel.getRoomPrices()[i] + "원  " + "  [기본인원] : "
                    + hotel.getRoomInfos()[i].getDefaultNumberPeople() + "명  " + "  [최대인원] : "
                    + hotel.getRoomInfos()[i].getMaxNumberPeople() + "명  " + "  [화장실] : "
                    + hotel.getRoomInfos()[i].getNumberBathroom() + "개  " + "  [침대] : "
                    + hotel.getRoomInfos()[i].getNumberBed() + "개  " + "  [주방] : " + kitchen);
        }
    }

    private void reserveRoom() {
        if (memberLoggedIn.getReservation() != null) {
            System.out.println("예약 추가·변경이 불가능합니다.");
            return;
        }
        Reservation r = new Reservation();
        setDate(r);
        setRoom(r);
        setNumberPeople(r);
        setService(r);
        memberLoggedIn.setReservation(r);
        System.out.println("예약이 완료됐습니다.");
        saveHotel();
    }

    /*
     * 변경사항
     *
     * 날짜형식 아니면 받지않음
     *
     * 작성자 : 정진호
     */
    private void setDate(Reservation r) { // 날짜 선택
        LocalDate today = LocalDate.now();
        HotelDate dateCheckIn;
        HotelDate dateCheckOut;
        while (true) {
            // TODO : 정규표현식으로 포맷 제한
            System.out.println("체크인 날짜를 입력해주세요. (20190314와 같이 입력해주세요.)");
            String checkIn = sc.nextLine();
            if (!checkIn.matches(
                    "^20(\\d{2})(((0([13578])|1([02]))(0[1-9]|[1-2][0-9]|3[0-1]))|((0([469])|11)(0[1-9]|[1-2][0-9]|30))|(02(0[1-9]|([12])[0-9]$)))")) {
                System.out.println("체크인 날짜를 선택해주세요.");
            } else {
                dateCheckIn = new HotelDate(checkIn);
                if (dateCheckIn.getCheckDate().isBefore(today)) {
                    System.out.println("선택 불가능한 날짜입니다. 다시 입력해주세요.");
                } else {
                    break;
                }
            }
        }

        while (true) {
            // TODO : 정규표현식으로 포맷 제한
            System.out.println("체크아웃 날짜를 입력해주세요. (20190314와 같이 입력해주세요.)");
            String checkOut = sc.nextLine();
            if (!checkOut.matches(
                    "^20(\\d{2})(((0([13578])|1([02]))(0[1-9]|[1-2][0-9]|3[0-1]))|((0([469])|11)(0[1-9]|[1-2][0-9]|30))|(02(0[1-9]|([12])[0-9]$)))")) {
                System.out.println("체크아웃 날짜를 선택해주세요.");
            } else {
                dateCheckOut = new HotelDate(checkOut);
                if (dateCheckOut.getCheckDate().isBefore(today)
                        || dateCheckOut.getCheckDate().isBefore(dateCheckIn.getCheckDate())
                        || dateCheckOut.getCheckDate().isEqual(dateCheckIn.getCheckDate())) {
                    System.out.println("선택 불가능한 날짜입니다. 다시 입력해주세요.");
                } else {
                    break;
                }
            }
        }

        System.out.println("체크인 날짜 : " + dateCheckIn.getCheckDate());
        System.out.println("체크아웃 날짜 : " + dateCheckOut.getCheckDate());

        r.setDateCheckIn(dateCheckIn);
        r.setDateCheckOut(dateCheckOut);

    }

    public void setRoom(Reservation r) {
        Period diff = Period.between(r.getDateCheckIn().getCheckDate(), r.getDateCheckOut().getCheckDate());
        System.out.println("예약 가능 객실 번호");

        List<Room> roomToReserve = new ArrayList<Room>();

        for (int i = 0; i < hotel.getRooms().size(); i++) {
            for (int j = 0; j < hotel.getRooms().get(i).size(); j++) {
                boolean canReserve = true;
                for (String guestId : hotel.getRooms().get(i).get(j).getGuests()) {
                    if (hotel.getRooms().get(i).get(j).getGuests().size() == 0) {
                        break;
                    }
                    LocalDate checkIn = hotel.getMembers().get(guestId).getReservation().getDateCheckIn()
                            .getCheckDate();
                    LocalDate checkOut = hotel.getMembers().get(guestId).getReservation().getDateCheckOut()
                            .getCheckDate();
                    if (checkIn.isBefore(r.getDateCheckIn().getCheckDate())
                            && checkOut.isAfter(r.getDateCheckIn().getCheckDate())
                            || checkIn.isEqual(r.getDateCheckIn().getCheckDate())
                            || checkOut.isEqual(r.getDateCheckOut().getCheckDate())) {
                        canReserve = false;
                        break;
                    } else if (checkIn.isBefore(r.getDateCheckOut().getCheckDate())
                            && checkOut.isAfter(r.getDateCheckOut().getCheckDate())
                            || checkIn.isEqual(r.getDateCheckIn().getCheckDate())
                            || checkOut.isEqual(r.getDateCheckOut().getCheckDate())) {
                        canReserve = false;
                        break;
                    } else if (checkIn.isAfter(r.getDateCheckIn().getCheckDate())
                            && checkIn.isBefore(r.getDateCheckOut().getCheckDate())
                            && checkOut.isAfter(r.getDateCheckIn().getCheckDate())
                            && checkOut.isBefore(r.getDateCheckOut().getCheckDate())
                            || checkIn.isEqual(r.getDateCheckIn().getCheckDate())
                            || checkOut.isEqual(r.getDateCheckOut().getCheckDate())) {
                        canReserve = false;
                        break;
                    }
                }
                if (canReserve) {
                    roomToReserve.add(hotel.getRooms().get(i).get(j));
                }
            }
            System.out.println();
        }

        for (int i = 0; i < roomToReserve.size(); i++) {
            System.out.println(roomToReserve.get(i).getRoomNumber() + "호 ");
        }

        Room room;
        System.out.println("예약할 방 번호를 입력해주세요");
        String roomNumber = sc.nextLine();
        char floor = roomNumber.charAt(0);
        char number = roomNumber.charAt(roomNumber.length() - 1);
        room = hotel.getRooms().get(floor - 50).get(number - 49); // char '1' = 49

        room.getGuests().add(memberLoggedIn.getId());
        r.setAmountPaid(r.getAmountPaid() + (hotel.getRoomPrices()[0] * diff.getDays()));
        System.out.println(
                "숙박일수 [" + diff.getDays() + "]\n숙박 요금 [" + CustomString.putComma(r.getAmountPaid()) + "]원 입니다.");
        r.setRoom(room);
    }

    /*
     * 변경사항 숙박일수 만큼 추가 인원비용 추가
     *
     * 작성자 : 정진호
     */
    private void setNumberPeople(Reservation r) {
        int numberPeople;
        int defaultNumberPeople = r.getRoom().getDefaultNumberPeople();
        int maxNumberPeople = r.getRoom().getMaxNumberPeople();
        Period diff = Period.between(r.getDateCheckIn().getCheckDate(), r.getDateCheckOut().getCheckDate());

        while (true) {
            System.out.println("숙박할 인원을 입력해주세요.");
            System.out.printf("선택하신 방의 기본 인원은 %d명, 최대 인원은 %d명입니다.\r\n", defaultNumberPeople, maxNumberPeople);
            System.out.println("인원 추가 시 50,000원이 추가됩니다.");
            numberPeople = Integer.parseInt(sc.nextLine());
            if (numberPeople > maxNumberPeople) {
                System.out.println("최대 인원을 초과했습니다.");
                System.out.println("다시 입력해주세요.");
            } else if (numberPeople > defaultNumberPeople) {
                System.out.println("추가 요금은 ["
                        + CustomString.putComma((numberPeople - defaultNumberPeople) * 50000 * diff.getDays())
                        + "]원입니다.");
                break;
            } else {
                break;
            }
        }

        if (numberPeople > defaultNumberPeople) {
            r.setAmountPaid(r.getAmountPaid() + (numberPeople - defaultNumberPeople) * 50000 * diff.getDays());
        }
        r.setNumberPeople(numberPeople);
    }

    /*
     * 변경사항
     *
     * 부가서비스 요금을 숙박 일수에 맞춰 표시 ( 인원 포함 ) 테라피는 몇일 숙박을 하던 단 1회만 받는다.
     *
     * 작성자 : 정진호
     */
    private void setService(Reservation r) { // 遺媛쒕퉬좏깮
        Period diff = Period.between(r.getDateCheckIn().getCheckDate(), r.getDateCheckOut().getCheckDate());
        long breakfast = diff.getDays() * hotel.getBreakfast() * r.getNumberPeople();
        long therapy = hotel.getTherapy() * r.getNumberPeople();
        System.out.println("부가 서비스를 선택해주세요.");
        System.out.println("조식은 일 50,000원, 전신 테라피는 300,000원입니다.");

        while (true) {
            System.out.println("1. 조식 2. 전신 테라피 3. 둘 다 4. 선택 안 함");
            String service = sc.nextLine();
            switch (service) {
                case "1":
                    System.out.println("조식을 선택하셨습니다.");
                    r.setBreakfast(true);
                    System.out.println("추가요금 : " + CustomString.putComma(breakfast) + "원 입니다");
                    r.setAmountPaid(r.getAmountPaid() + breakfast);
                    return;
                case "2":
                    System.out.println("전신 테라피를 선택하셨습니다.");
                    r.setTherapy(true);
                    System.out.println("추가요금 : " + CustomString.putComma(therapy) + "원 입니다");
                    r.setAmountPaid(r.getAmountPaid() + therapy);
                    return;
                case "3":
                    System.out.println("조식과 전신 테라피를 선택하셨습니다.");
                    r.setBreakfast(true);
                    r.setTherapy(true);
                    System.out.println("조식 추가요금 : [" + CustomString.putComma(breakfast) + "]원 입니다.");
                    System.out.println("테라피 추가요금 : [" + CustomString.putComma(therapy) + "]원 입니다.");
                    r.setAmountPaid(r.getAmountPaid() + breakfast);
                    r.setAmountPaid(r.getAmountPaid() + therapy);
                    return;
                case "4":
                    System.out.println("서비스를 선택하지 않으셨습니다.");
                    return;
                default:
                    System.out.println("잘못 입력하셨습니다.");
            }
        }
    }
    /*
     * 변경사항
     *
     * 예약 확인 기능에 예약 변경, 예약 취소 기능을 추가함
     *
     * 작성자 : 정진호
     */

    private void getReservation() { // 예약 확인
        if (memberLoggedIn.getReservation() == null) {
            System.out.println("예약 기록이 없습니다.");
            return;
        }

        System.out.println(memberLoggedIn.getReservation().checkReservation());

        System.out.println("┎                                  ┒");
        System.out.println("         1. 예약 변경하기");
        System.out.println();
        System.out.println("         2. 예약 취소하기");
        System.out.println();
        System.out.println("         3.  돌아가기");
        System.out.println("┖                                  ┚");
        String select = sc.nextLine();
        switch (select) {
            case "1":
                System.out.println("예약을 변경합니다.");
                changeReservation();
                break;
            case "2":
                System.out.println("예약을 취소합니다.");
                cancelReservation();
            case "3":
                System.out.println("메인화면으로 돌아갑니다.");
                break;
            default:
                System.out.println("잘못 입력하였습니다.");
                getReservation();
                break;
        }
    }

    /*
     * 덉빟 痍⑥냼 ⑥닔
     *
     * 뚯썝덉빟諛⑹씠 媛吏怨덈뒗 덉빟뚯썝 뺣낫먯꽌 뚯썝 젣 뚯썝덉빟 곹깭 null濡꾪솚
     *
     * 묒꽦: ㅼ쥌
     */
    private void cancelReservation() { // 예약 취소
        Period diff = Period.between(hotel.getToday(), memberLoggedIn.getReservation().getDateCheckIn().getCheckDate());
        if (diff.getDays() < 3) {
            System.out.println("예약 변경·취소는 체크인 3일 전까지만 가능합니다.");
            return;
        }
        memberLoggedIn.getReservation().getRoom().getGuests().remove(memberLoggedIn.getId());
        memberLoggedIn.setReservation(null);
        saveHotel();
        System.out.println("기존 예약이 취소되었습니다.");
    }

    /*
     * 예약 변경 함수
     *
     * 예약 취소 후 예약하기 실행
     *
     * 작성자 : 윤종석
     */
    private void changeReservation() { // 예약 변경
        cancelReservation();
        reserveRoom();
    }

    /*
     * // 회원 가입 //
     *
     * 회원 가입 후 자동 로그인
     *
     * 작성자 : 정진호
     */

    private void signUp() {
        String id;
        String name;
        String password;
        String phoneNumber;
        String birthday;

        while (true) {
            System.out.println("회원가입 절차");
            System.out.println("이름을 입력해 주세요.");
            name = sc.nextLine();
            if (name.length() > 10) {
                System.out.println("이름이 너무 깁니다.");
            } else if (!name.matches("^[가-힣]*$")) {
                System.out.println("한글만 입력할수 있습니다. ( 자음 불가 )");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("아이디를 입력해주세요. (4자 이상  10자 이내)");
            id = sc.nextLine();
            if (id.length() > 10 || id.length() < 4) {
                System.out.println("ID의 길이가 올바르지 않습니다.");
            } else if (!id.matches("\\p{Alnum}+")) {
                System.out.println("영어,숫자만 입력해주세요.");
            } else if (hotel.getMembers().containsKey(id)) {
                System.out.println("동일한 ID가 존재합니다.");
                System.out.println("다른 아이디를 입력해주세요.");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("비밀번호를 입력해주세요. (6자이상 10자 이내)");
            password = sc.nextLine();
            if (password.length() > 10 || password.length() < 6) {
                System.out.println("PWD의 길이가 올바르지 않습니다.");
            } else if (!password.matches("\\p{Alnum}+")) {
                System.out.println("영어,숫자만 입력해주세요.");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("핸드폰번호를 입력해주세요. ( - 생략)");
            System.out.println("Ex) 01012345678");
            phoneNumber = sc.nextLine();
            if (phoneNumber.length() > 12 || phoneNumber.length() < 11) {
                System.out.println("전화번호의 형식이 잘못됐습니다 ( 길이 )");
            } else if (!phoneNumber.matches("^010[0-9]{8}")) {
                System.out.println("잘못 입력하였습니다.");
            } else {
                break;
            }
        }

        while (true) {
            System.out.println("생년월일 8자리를 입력해주세요.");
            System.out.println("Ex)96년생 7월 15일생이면 >> 19960715");
            birthday = sc.nextLine();
            if (birthday.length() > 9 || birthday.length() <= 7) {
                System.out.println("생년월일의 형식이 잘못 됐습니다 ( 길이 )");
            } else if (!birthday.matches(
                    "^(19[0-9]|200)[0-9](((0[13578]|1[02])(0[1-9]|[1-2][0-9]|3[0-1]))|((0[469]|11)(0[1-9]|[1-2][0-9]|30))|(02(0[1-9]|[12][0-9]$)))")) {
                System.out.println("잘못된 형식입니다. 다시 입력해주세요.");
            } else {
                break;
            }
        }

        System.out.println("성공적으로 회원가입을 하셨습니다.!!");
        System.out.println(name + "님 환영합니다^^~");
        loginCheck = true;
        memberLoggedIn = new Member(id, name, password, phoneNumber, birthday);
        hotel.getMembers().put(id, memberLoggedIn);
        saveHotel();
    }

    /*
     * 로그인
     *
     * 시스템 재실행시 회원 가입 된 회원 id를 로그인
     *
     * 작성자 : 정진호
     */
    private void login() {
        System.out.println("로그인");
        System.out.println("ID입력");
        String id = sc.nextLine();
        System.out.println("비밀번호 입력");
        String pwd = sc.nextLine();
        if (!hotel.getMembers().containsKey(id)) {
            System.out.println("ID를 확인해주세요.");
        } else {
            if (!hotel.getMembers().get(id).getPassword().equals(pwd)) {
                System.out.println("비밀번호를 확인해주세요.");
            } else {
                loginCheck = true;
                memberLoggedIn = hotel.getMembers().get(id);
                System.out.println("로그인 성공!");
            }
        }
        saveHotel();
    }

    /*
     * 회원 정보 수정
     *
     * 로그인 된 회원이 본인의 회원 정보를 수정한다.
     *
     * 작성자 : 정진호
     */
    private void changeInfo() {
        String password;
        String password2;
        String phone;
        exit:
        while (true) {
            System.out.println("회원 정보 수정입니다.");
            System.out.println("┎                                  ┒");
            System.out.println("         1. 비밀번호 재설정");
            System.out.println();
            System.out.println("         2. 전화번호 재설정");
            System.out.println();
            System.out.println("         3. 회원 탈퇴");
            System.out.println();
            System.out.println("         4. 돌아가기");
            System.out.println("┖                                  ┚");
            String number = sc.nextLine();

            switch (number) {
                case "1":
                    System.out.println("비밀번호 재설정입니다.");
                    System.out.println("바꾸실 비밀번호를 입력해주세요. (6자이상 10자 이내)");
                    password = sc.nextLine();
                    System.out.println("다시 한번 입력해주세요.");
                    password2 = sc.nextLine();
                    if (password.equals(password2)) {
                        memberLoggedIn.setPassword(password2);
                        System.out.println("비밀번호가 바뀌었습니다.");
                    } else if (password.length() > 10 || password.length() < 6) {
                        System.out.println("PWD의 길이가 올바르지 않습니다.");
                    } else if (!password.matches("\\p{Alnum}+")) {
                        System.out.println("영어,숫자만 입력해주세요.");
                    } else {
                        System.out.println("비밀번호가 일치하지 않습니다.");
                        System.out.println("다시 시도 해 주세요.");
                    }
                    saveHotel();
                    break;

                case "2":
                    System.out.println("전화번호 재 설정입니다.");
                    System.out.println("바꾸실 전화번호를 입력해주세요.");
                    phone = sc.nextLine();
                    if (phone.matches("^010[0-9]{8}")) {
                        memberLoggedIn.setPhoneNumber(phone);
                        System.out.println("바꾸신 핸드폰 번호입니다.");
                        System.out.println("바뀐 전화번호 : " + phone);
                    } else {
                        System.out.println("잘못된 핸드폰번호입니다.");
                        System.out.println("Ex)01012341234 ( - 제외 )");
                    }
                    saveHotel();
                    break;
                case "3":
                    quit();
                    break exit;
                case "4":
                    break exit;
                default:
                    System.out.println("잘못된 메뉴 번호입니다. 다시 입력해주세요.");
                    break;
            }
        }
    }

    /*
     * 회원 탈퇴
     *
     * 로그인된 회원의 회원을 삭제한다.
     *
     * 작성자 : 정진호
     */
    private void quit() {

        System.out.println("회원 탈퇴를 하시겠습니까?");
        System.out.println("┎                                  ┒");
        System.out.println("         1.   예");
        System.out.println();
        System.out.println("         2.   아니오");
        System.out.println("┖                                  ┚");
        String select = sc.nextLine();
        switch (select) {
            case "1":
                System.out.println("1. 회원 탈퇴 하였습니다.");
                memberLoggedIn.getReservation().getRoom().getGuests().remove(memberLoggedIn.getId());
                memberLoggedIn.setReservation(null);
                hotel.getMembers().remove(memberLoggedIn.getId(), memberLoggedIn);
                loginCheck = false;
                saveHotel();
                break;
            case "2":
                System.out.println("취소 하였습니다.");
                break;
            default:
                System.out.println("잘못된 메뉴 번호입니다.");
                quit();
                break;
        }

    }

    /*
     * 메뉴
     *
     * 비로그인 , 로그인 회원의 보이는 메뉴가 다르다. 회원 가입을 하면 자동 로그인이 됨으로 로그인회원 화면이 보이게 된다.
     *
     * 작성자 : 정진호
     */
    private void printMenu() {
        while (true) {
            System.out.println("2조 호텔에 오신걸 환영합니다.");
            System.out.println("┎                                  ┒");
            System.out.println("         1.   객실 보기");
            System.out.println();
            if (!loginCheck) {
                System.out.println("         2.   로그인");
                System.out.println();
                System.out.println("         3.   회원 가입");
                System.out.println();
                System.out.println("         4.   종료 하기");
                System.out.println("┖                                  ┚");
            } else {
                System.out.println("         2. 객실 예약하기");
                System.out.println();
                System.out.println("         3. 예약 확인하기");
                System.out.println();
                System.out.println("         4. 회원 정보 수정");
                System.out.println();
                System.out.println("         5.   종료 하기");
                System.out.println("┖                                  ┚");
                System.out.println(memberLoggedIn.getName() + "님 방문을 환영합니다.");
            }

            String select = sc.nextLine();
            switch (select) {
                case "1":
                    System.out.println("현재 객실 정보현황 입니다.");
                    getRoomInfo();
                    break;
                case "2":
                    if (!loginCheck) {
                        login();
                    } else {
                        System.out.println("객실 예약입니다.");
                        reserveRoom();
                    }
                    break;
                case "3":
                    if (!loginCheck) {
                        signUp();
                    } else {
                        getReservation();
                    }
                    break;
                case "4":
                    if (!loginCheck) {
                        saveHotel();
                        return;
                    } else {
                        changeInfo();
                    }
                    break;
                case "5":
                    if (!loginCheck) {
                        System.out.println("번호를 잘못 입력했습니다.");
                    } else {
                        saveHotel();
                        return;
                    }
                    break;
                default:
                    System.out.println("번호를 잘못 입력했습니다.");
                    break;
            }
        }
    }
}
