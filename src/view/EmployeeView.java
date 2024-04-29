package view;

import business.*;
import core.ComboItem;
import core.Helper;
import dao.BookingDao;
import entity.Booking;
import entity.Hotel;
import entity.Room;
import entity.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.ParseException;
import java.util.ArrayList;

public class EmployeeView extends Layout {
    private JPanel container;
    private JTabbedPane tabbedPane1;
    private JButton btn_logout;
    private JTable tbl_hotels;
    private JButton btn_update;
    private JButton btn_delete;
    private JButton btn_add;
    private JLabel lbl_employee;
    private JLabel lbl_welcome;
    private JLabel lbl_update;
    private JLabel lbl_delete;
    private JLabel lbl_add;
    private SeasonManager seasonManager = new SeasonManager();
    private PensionManager pensionManager = new PensionManager();
    private JPopupMenu season_menu;
    private JButton btn_season_add;
    private JTable tbl_pension;
    private JPopupMenu pension_menu;
    private JButton btn_pension_add;
    private JTable tbl_room;
    private JScrollPane btn_room_search;
    private JComboBox<ComboItem> cmb_hotel_name;
    private JComboBox<ComboItem> cmb_city;
    private JTextField fld_date_entry_room;
    private JTextField fld_date_release_room;
    private JButton room_search_btn;
    private JButton btn_room_clean;
    private JButton btn_room_add;
    private JTable tbl_booking;
    private JPanel pnl_booking;
    private JScrollPane scl_booking;
    private JButton btn_addbooking;
    private JTable tbl_season;
    private JButton btn_reservation;
    private JButton btn_booking_update;
    private JButton btn_booking_delete;
    private User user;
    private DefaultTableModel tmdl_hotels = new DefaultTableModel();
    private HotelManager hotelManager;
    private JPopupMenu HotelMenu;
    private JPopupMenu BookingMenu;
    private Object[] col_room;
    DefaultTableModel tmdl_season = new DefaultTableModel();
    DefaultTableModel tmdl_pension = new DefaultTableModel();
    private JPopupMenu room_menu;
    private JPopupMenu reservation_menu;
    private RoomManager roomManager = new RoomManager();
    DefaultTableModel tmdl_room = new DefaultTableModel();
    DefaultTableModel tmdl_res = new DefaultTableModel();
    private Room room;
    private Booking booking;
    private BookingDao bookingDao;
    private BookingManager bookingManager;
    private JPopupMenu booking_menu;
    private DefaultTableModel default_table_booking = new DefaultTableModel();
    String hotelName = null;
    String selectedCity = null;

    public EmployeeView(User user) {

        this.hotelManager = new HotelManager();
        this.season_menu = new JPopupMenu();
        this.pension_menu = new JPopupMenu();
        this.bookingManager = new BookingManager();
        this.room_menu = new JPopupMenu();
        add(container);
        this.guiInitilaze(1500, 1000);

        this.user = user;
        if (this.user == null) {
            dispose();
        }
        this.lbl_welcome.setText("Welcome : " + this.user.getName());
        loadHotelTable();
        loadHotelComponent();

        loadSeasonTableComponent();
        loadSeasonTable();

        loadPensionTable();
        loadRoomTable(null);
        LoadRoomTableComponent();


        loadBookingTable();
        loadBookingComponent();

        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel_name.addItem(new ComboItem(hotel.getId(), hotel.getName()));
        }
        this.cmb_hotel_name.setSelectedItem(null);
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_city.addItem(new ComboItem(hotel.getId(), hotel.getCity()));
        }
        this.cmb_city.setSelectedItem(null);
        room_search_btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField[] roomJTextField = new JTextField[]{fld_date_entry_room, fld_date_release_room};


                ArrayList<Room> roomList = roomManager.searchForRooms(
                        fld_date_entry_room.getText(),
                        fld_date_release_room.getText(), selectedCity, hotelName

                );


                ArrayList<Object[]> searchResult = roomManager.getForTable(col_room.length, roomList);
                loadRoomTable(searchResult);

            }


        });
        btn_booking_delete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedReservationId = getTableSelectedRow(tbl_booking, 0);
                int selectedRoomId = getTableSelectedRow(tbl_booking, 1);
                if (selectedReservationId != -1) {
                    if (Helper.confirm("sure")) {
                        if (bookingManager.delete(selectedReservationId)) {
                            Helper.showMsg("done");

                            // Rezervasyon silindiğinde odaya ait stok miktarını artır
                            Room room = roomManager.getById(selectedRoomId);
                            room.setStock(room.getStock() + 1);
                            roomManager.updateStock(room);

                            loadBookingTable();
                            loadRoomTable(null);

                        } else {
                            Helper.showMsg("error");
                        }
                    }
                } else {
                    JOptionPane.showMessageDialog(EmployeeView.this, "Please select a Reservation.", "No Reservation Selected", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        btn_booking_update.addActionListener(e -> {
            int selectedRoomId = this.getTableSelectedRow(tbl_booking, 1);
            int selectedHotelId = this.getTableSelectedRow(tbl_booking, 12);
            int selectedPensionId = this.getTableSelectedRow(tbl_booking, 13);
            int selectedSeasonId = this.getTableSelectedRow(tbl_booking, 14);
            int selectedBookingId = this.getTableSelectedRow(tbl_booking, 0);
            if (selectedRoomId != -1) {
                UpdateBookingView updateBookingView = new UpdateBookingView(this.roomManager.getById(selectedRoomId), this.hotelManager.getById(selectedHotelId), this.pensionManager.getById(selectedPensionId), this.seasonManager.getById(selectedSeasonId), this.bookingManager.getById(selectedBookingId));
                updateBookingView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                        loadBookingTable();
                        loadBookingComponent();
                    }
                });
            } else {
                JOptionPane.showMessageDialog(EmployeeView.this, "Please select a hotel.", "No Hotel Selected", JOptionPane.WARNING_MESSAGE);
            }
        });
        cmb_hotel_name.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComboItem hotelNameComboItem = (ComboItem) cmb_hotel_name.getSelectedItem();
                if (hotelNameComboItem != null) {
                    hotelName = hotelNameComboItem.getValue();
                } else {
                    hotelName = null;
                }
            }
        });
        cmb_city.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ComboItem hotelNameComboItem = (ComboItem) cmb_city.getSelectedItem();
                if (hotelNameComboItem != null) {
                    selectedCity = hotelNameComboItem.getValue();
                } else {
                    selectedCity = null;
                }
            }
        });
    }


    public void loadHotelTable() {
        Object[] col_hotel_list = {"Otel ID", "Otel Name", "City ", " Area ", "Adress", " E-Mail", "Phone", "Star", "Car Park", "Wifi", "Pool", "Fitness", "Concierge", "Spa", "Room Services"};
        ArrayList<Object[]> hotelList = this.hotelManager.getForTable(col_hotel_list.length);
        this.createTable(this.tmdl_hotels, this.tbl_hotels, col_hotel_list, hotelList);
    }

    public void loadSeasonTable() {
        Object[] col_season = {"ID", "Hotel ID", "Start Date", "Finish Date"};
        ArrayList<Object[]> seasonList = this.seasonManager.getForTable(col_season.length, this.seasonManager.findAll());
        createTable(this.tmdl_season, tbl_season, col_season, seasonList);

    }

    public void loadPensionTable() {
        Object[] col_pension = {"ID", "Hotel ID", "Pension Type"};
        ArrayList<Object[]> pensionList = this.pensionManager.getForTable(col_pension.length, this.pensionManager.findAll());
        createTable(this.tmdl_pension, tbl_pension, col_pension, pensionList);
    }

    public void loadRoomTable(ArrayList<Object[]> roomList) {
        col_room = new Object[]{"ID", "Hotel ID", "Hotel Name", "City", "Pension ID", "Season ID", "Type", "Stock", "Adult Price", "Child Price", "Bed Capacity", "Square Meter", "Television", "Minibar", "Game Console", "Cash BOX", "Projection"};
        if (roomList == null) {
            roomList = roomManager.getForTable(col_room.length, this.roomManager.findAll());
        }
        createTable(this.tmdl_room, tbl_room, col_room, roomList);
    }

    private void loadBookingTable() {
        Object[] columnsOfBookingTable = {"Rezervation.ID", "Room ID", "Guest Name", "Guest ID", "Email", "Phone", "Adult", "Child", "Check-in", "Checkout", "Note", "Total Price", "Hotel Id", "P.ID", "Season ID"};
        ArrayList<Object[]> bookingList = this.bookingManager.getForTable(columnsOfBookingTable.length, this.bookingManager.findAll());

        this.createTable(this.default_table_booking, this.tbl_booking, columnsOfBookingTable, bookingList);
    }


    private void loadBookingComponent() {
        //get the reservation the user clicked
        tableRowSelect(this.tbl_booking);

        //add the popup menu to the component
        this.tbl_booking.setComponentPopupMenu(booking_menu);
    }


    public void LoadRoomTableComponent() {

        tableRowSelect(tbl_room);

        btn_reservation.addActionListener(e -> {
            int selectedRoomId = this.getTableSelectedRow(tbl_room, 0);
            int selectedHotelId = this.getTableSelectedRow(tbl_room, 1);
            int selectedPensionId = this.getTableSelectedRow(tbl_room, 4);
            int selectedSeasonId = this.getTableSelectedRow(tbl_room, 5);
            if (selectedRoomId != -1) {
                AddBookingView addBookingView = new AddBookingView(this.roomManager.getById(selectedRoomId), this.hotelManager.getById(selectedHotelId), this.pensionManager.getById(selectedPensionId), this.seasonManager.getById(selectedSeasonId));
                addBookingView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                        loadBookingTable();
                        loadBookingComponent();
                    }
                });
            } else {
                JOptionPane.showMessageDialog(EmployeeView.this, "Please select a hotel.", "No Hotel Selected", JOptionPane.WARNING_MESSAGE);
            }
        });
        room_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_room, 0);
                if (this.roomManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadRoomTable(null);

                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_room.setComponentPopupMenu(room_menu);
    }


    public void loadSeasonTableComponent() {

        tableRowSelect(tbl_season);
        season_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_season, 0);
                if (this.seasonManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadSeasonTable();

                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_season.setComponentPopupMenu(season_menu);
    }

    public void loadHotelComponent() {
        btn_logout.addActionListener(e -> {
            LoginView loginView = new LoginView();
            dispose();
        });

        this.tableRowSelect(this.tbl_hotels);
        this.HotelMenu = new JPopupMenu();
        this.HotelMenu.add("Add").addActionListener(e -> {
            NewHotelView newHotelView = new NewHotelView(new Hotel());
            newHotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();

                }
            });

        });

        btn_pension_add.addActionListener(e -> {
            int selectId = this.getTableSelectedRow(tbl_hotels, 0);
            AddPensionView addPensionView = new AddPensionView(selectId);
            addPensionView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {

                    loadPensionTable();
                }
            });
        });

        btn_add.addActionListener(e -> {
            NewHotelView newHotelView = new NewHotelView(new Hotel());
            newHotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();

                }
            });
        });

        this.HotelMenu.add("Update").addActionListener(e -> {
            int selectHotelId = this.getTableSelectedRow(tbl_hotels, 0);
            NewHotelView hotelView = new NewHotelView(this.hotelManager.getById(selectHotelId));
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();

                }
            });
        });

        btn_room_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddRoomView addRoomView = new AddRoomView();
                addRoomView.addWindowListener(new WindowAdapter() {
                    @Override
                    public void windowClosed(WindowEvent e) {
                        loadRoomTable(null);
                    }
                });
            }
        });

        this.btn_room_clean.addActionListener(e -> {
            this.cmb_hotel_name.setSelectedItem(null);
            this.cmb_city.setSelectedItem(null);
            this.fld_date_entry_room.setText(null);
            this.fld_date_release_room.setText(null);
            loadRoomTable(null);
        });

        btn_update.addActionListener(e -> {

            int selectHotelId = this.getTableSelectedRow(tbl_hotels, 0);
            NewHotelView hotelView = new NewHotelView(this.hotelManager.getById(selectHotelId));
            hotelView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadHotelTable();

                }
            });
        });

        this.HotelMenu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(tbl_hotels, 0);
                if (this.hotelManager.delete(selectUserId)) {
                    Helper.showMsg("done");
                    loadHotelTable();
                } else {
                    Helper.showMsg("error");
                }
            }

        });
        this.tableRowSelect(this.tbl_pension);
        pension_menu.add("Delete").addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectBrandId = this.getTableSelectedRow(tbl_pension, 0);
                if (this.pensionManager.delete(selectBrandId)) {
                    Helper.showMsg("done");
                    loadPensionTable();


                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_pension.setComponentPopupMenu(pension_menu);

        btn_season_add.addActionListener(e -> {
            AddSeasonView addSeasonView = new AddSeasonView();
            addSeasonView.addWindowListener(new WindowAdapter() {
                @Override
                public void windowClosed(WindowEvent e) {
                    loadSeasonTable();
                }
            });
        });

        btn_delete.addActionListener(e -> {
            if (Helper.confirm("sure")) {
                int selectUserId = this.getTableSelectedRow(tbl_hotels, 0);
                if (this.hotelManager.delete(selectUserId)) {
                    Helper.showMsg("done");
                    loadHotelTable();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
        this.tbl_hotels.setComponentPopupMenu(this.HotelMenu);

    }

    private void createUIComponents() throws ParseException {
        this.fld_date_entry_room = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_date_entry_room.setText("01/01/2024");
        this.fld_date_release_room = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.fld_date_release_room.setText("01/05/2024");
    }
}

