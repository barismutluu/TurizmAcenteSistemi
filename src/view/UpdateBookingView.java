package view;

import business.BookingManager;
import business.RoomManager;
import business.SeasonManager;
import core.Helper;
import entity.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;


public class UpdateBookingView extends Layout {
    private JPanel container;
    private JTextField tf_res_hotel_name;
    private JTextField tf_res_city;
    private JTextField tf_res_star;
    private JTextField tf_res_roomtype;
    private JTextField tf_res_room_field;
    private JTextField tf_res_bed_capacity;
    private JTextField tf_res_start_date;
    private JTextField tf_res_end_date1;
    private JRadioButton rbut_television;
    private JRadioButton rbut_game_console;
    private JRadioButton rbut_cash_vault;
    private JRadioButton rbut_res_projection;
    private JRadioButton rbut_res_minibar;
    private JTextField tf_res_pension;
    private JButton btn;
    private JFormattedTextField star_date;
    private JFormattedTextField end_date;
    private JTextField txtf_region;
    private JTextField txtf_address;
    private JTextField txtf_phne;
    private JTextField txtf_season;
    private JTabbedPane tabbedPane3;
    private JButton btn_calculate;
    private JTextField txtf_total_amount;
    private JTextField txtf_guest_name;
    private JTextField txtf_guest_phone;
    private JTextField txtf_guest_email;
    private JTextField txtf_guest_id;
    private JTextField txtf_guest_note;
    private JButton btn_save;
    private JComboBox cmbx_adult;
    private JComboBox cmbx_children;
    private JTextField txtf_adult_price;
    private JTextField txtf_children_price;
    private BookingManager bookingManager = new BookingManager();
    private Season season;
    private Pension pension;
    private SeasonManager seasonManager;
    private RoomManager roomManager;
    private double totalAmount;
    private Hotel hotel;
    DefaultTableModel mdl_FacilitiesMdl1 = new DefaultTableModel();
    private Room room;
    public DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private Booking booking;


    public UpdateBookingView(Room room, Hotel hotel, Pension pension, Season season, Booking booking) {
        this.add(container);
        this.guiInitilaze(1000, 700);
        this.hotel = hotel;
        this.pension = pension;
        this.season = season;
        this.room = room;
        this.booking = booking;
        this.roomManager = new RoomManager();

        loadReservationComponent();

        // Populate fields with reservation details
        this.tf_res_hotel_name.setText(room.getHotel().getName());
        this.tf_res_city.setText(room.getHotel().getCity());
        this.txtf_region.setText(hotel.getArea());
        this.txtf_address.setText(hotel.getAdress());
        this.txtf_phne.setText(hotel.getPhone());
        this.tf_res_star.setText(hotel.getStars());


        this.txtf_guest_name.setText(booking.getGuest_name());
        this.txtf_guest_phone.setText(booking.getGuest_phone());
        this.txtf_guest_email.setText(booking.getGuest_email());
        this.txtf_guest_id.setText(booking.getGuest_id());
        this.txtf_guest_note.setText(booking.getGuest_note());

        this.tf_res_roomtype.setText(room.getType());
        this.tf_res_pension.setText(pension.getPension_type());
        this.tf_res_bed_capacity.setText(String.valueOf(room.getBed_capacity()));
        this.tf_res_room_field.setText(String.valueOf(room.getSquare_meter()));
        this.txtf_season.setText(String.valueOf(season.getStart_date()) + " to " + String.valueOf(season.getFinish_date()));
        this.txtf_adult_price.setText(String.valueOf(this.room.getAdult_price()));
        this.txtf_children_price.setText(String.valueOf(this.room.getChild_price()));
        this.rbut_television.setSelected(room.isTelevision());
        this.rbut_game_console.setSelected(room.isGame_console());
        this.rbut_cash_vault.setSelected(room.isCash_box());
        this.rbut_res_projection.setSelected(room.isProjection());
        this.rbut_res_minibar.setSelected(room.isMinibar());

        // Populate adult count dropdown
        int adult = room.getBed_capacity();
        for (int i = 0; i <= adult; i++) {
            this.cmbx_adult.addItem(i);
        }

        // Populate children count dropdown based on room type
        String type = room.getType();
        int child;
        if (type.equals("Single Room")) {
            child = 1;
            for (int i = 0; i <= child; i++) {
                this.cmbx_children.addItem(i);
            }
        } else if (type.equals("Double Room")) {
            child = 2;
            for (int i = 0; i <= child; i++) {
                this.cmbx_children.addItem(i);
            }
        } else if (type.equals("Junior Suite Room")) {
            child = 3;
            for (int i = 0; i <= child; i++) {
                this.cmbx_children.addItem(i);
            }
        } else if (type.equals("Suite Room")) {
            child = 5;
            for (int i = 0; i <= child; i++) {
                this.cmbx_children.addItem(i);
            }
        }

        // Calculate total amount based on selected dates and guest counts
        LocalDate checkInDate = LocalDate.parse(star_date.getText(), this.formatter);
        LocalDate checkOutDate = LocalDate.parse(end_date.getText(), formatter);
        long dayCount = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

        double adultPrice = this.room.getAdult_price();
        double childrenPrice = this.room.getChild_price();
        int adultCount = Integer.parseInt(this.cmbx_adult.getSelectedItem().toString());
        int childCount = Integer.parseInt(cmbx_children.getSelectedItem().toString());

        totalAmount = ((adultPrice * adultCount) + (childrenPrice * childCount)) * dayCount;

        this.txtf_total_amount.setText(String.valueOf(totalAmount));
    }

    private void loadReservationComponent() {
        btn_save.addActionListener(e -> {
            JTextField[] checkField = {this.txtf_guest_name, this.txtf_guest_phone, this.txtf_guest_email, this.txtf_guest_id};
            if (Helper.isFieldListEmpty(checkField)) {
                Helper.showMsg("Lütfen tüm alanları doldurun.");
            } else {
                // Set reservation details
                this.booking.setRoom_id(this.room.getId());
                this.booking.setGuest_name(this.txtf_guest_name.getText());
                this.booking.setGuest_id(this.txtf_guest_id.getText());
                this.booking.setGuest_email(this.txtf_guest_email.getText());
                this.booking.setGuest_phone(this.txtf_guest_phone.getText());
                int adultCount = Integer.parseInt(this.cmbx_adult.getSelectedItem().toString());
                int childCount = Integer.parseInt(cmbx_children.getSelectedItem().toString());
                this.booking.setAdult_count(adultCount);
                this.booking.setChild_count(childCount);
                this.booking.setCheck_in_date(LocalDate.parse(this.star_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.booking.setCheck_out_date(LocalDate.parse(this.end_date.getText(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
                this.booking.setGuest_note(this.txtf_guest_note.getText());
                this.booking.setTotal_price(Double.parseDouble(this.txtf_total_amount.getText()));
                this.booking.setHotel_id(this.hotel.getId());
                this.booking.setPension_id(this.booking.getPension_id());
                this.booking.setSeason_id(this.booking.getSeason_id());

                // Update the reservation
                boolean result = this.bookingManager.update(this.booking);
                if (result) {
                    Helper.showMsg("Rezervasyon başarıyla kaydedildi.");
                    dispose();
                } else {
                    Helper.showMsg("Rezervasyon kaydedilirken bir hata oluştu.");
                }
            }
        });

        btn_calculate.addActionListener(e -> {
            LocalDate checkInDate = LocalDate.parse(star_date.getText(), formatter);
            LocalDate checkOutDate = LocalDate.parse(end_date.getText(), formatter);
            long dayCount = ChronoUnit.DAYS.between(checkInDate, checkOutDate);

            double adultPrice = this.room.getAdult_price();
            double childrenPrice = this.room.getChild_price();
            int adultCount = Integer.parseInt(this.cmbx_adult.getSelectedItem().toString());
            int childCount = Integer.parseInt(cmbx_children.getSelectedItem().toString());

            totalAmount = ((adultPrice * adultCount) + (childrenPrice * childCount)) * dayCount;

            this.txtf_total_amount.setText(String.valueOf(totalAmount));
        });
    }


    private void createUIComponents() throws ParseException {
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate tomorrow = today.plusDays(1);

        // Set default start and end dates
        this.star_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.star_date.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        this.end_date = new JFormattedTextField(new MaskFormatter("##/##/####"));
        end_date.setText(tomorrow.format(formatter));
    }

}