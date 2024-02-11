package org.firstinspires.ftc.teamcode.tuning;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.Disabled;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;


import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.CRServo;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;
import com.qualcomm.robotcore.util.Range;


import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.apriltag.AprilTagDetection;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;


import org.firstinspires.ftc.teamcode.MecanumDrive;
import java.util.ArrayList;


@Autonomous
public class BlueRightTheory extends LinearOpMode {
    OpenCvCamera camera;

    static final double FEET_PER_METER = 3.28084;

    int LEFT = 0;
    int MIDDLE = 1;
    int RIGHT = 2;
    // AprilTagDetection tagOfInterest = null;
    DcMotor leftWinch;
    DcMotor rightWinch;

    DcMotor intake;

    DcMotor shooter;

    Servo hips;
    Servo arch;
    Servo frontLeg;
    Servo backLeg;

    Servo sneakyLink; // facing robot (sneaky link suuuus)
    Servo sneakyRink;
    MecanumDrive drive;
    int zone = 0;


    private ElapsedTime runtime = new ElapsedTime();


    @Override
    public void runOpMode () throws InterruptedException {
        leftWinch = hardwareMap.dcMotor.get("leftWinch");
        rightWinch = hardwareMap.dcMotor.get("rightWinch");
        shooter = hardwareMap.dcMotor.get("shooter");
        intake = hardwareMap.dcMotor.get("intake");
        hips = hardwareMap.servo.get("hips");
        arch = hardwareMap.servo.get("arch");
        frontLeg = hardwareMap.servo.get("frontLeg");
        backLeg = hardwareMap.servo.get("backLeg");
        sneakyLink = hardwareMap.servo.get("sneakyLink");
        sneakyRink = hardwareMap.servo.get("sneakyRink");
        drive = new MecanumDrive(hardwareMap, new Pose2d(-36, 63, Math.toRadians(90)));
        TeamPropDetector.startPropDetection(hardwareMap, telemetry);
        drive.setTelemetry(telemetry);


        rightWinch.setDirection(DcMotorSimple.Direction.REVERSE);
        leftWinch.setDirection((DcMotorSimple.Direction.FORWARD));


        leftWinch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        rightWinch.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);


        leftWinch.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        rightWinch.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        rightWinch.setTargetPosition(0);
        leftWinch.setTargetPosition(0);
//        chainBar.setTargetPosition(ARM_0);
        rightWinch.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        leftWinch.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        hips.setPosition(.22); // og is .22
        arch.setPosition(.69);
        backLeg.setPosition(.77);  // .9 is closed pos
        frontLeg.setPosition(.52); //  .6 is closed pos
        sneakyLink.setPosition(1); //  up from 0
        sneakyRink.setPosition(0); // down from 0


        waitForStart();
        zone = TeamPropDetector.getBluePropZone();
        TeamPropDetector.endPropDetection();
        //Actions.runBlocking(drive.actionBuilder(new Pose2d(12.00, 63, Math.toRadians(90))).strafeTo(new Vector2d(12,30)).build());
        //Drive to SPIke Mark and get from stack
        if (zone == 1) {
            Actions.runBlocking(drive.actionBuilder(new Pose2d(-36.00, 63.00, Math.toRadians(90.00)))
                    .strafeTo(new Vector2d(-40.8, 55.78))
                    .strafeToLinearHeading(new Vector2d(-25.55, 36.84), Math.toRadians(120.00))
                    .strafeToLinearHeading(new Vector2d(-50.24, 50.35), Math.toRadians(180.00))
                    .splineToConstantHeading(new Vector2d(49.89, 58.66), Math.toRadians(0.00))
                    .build());
            backLeg.setPosition(.82);
            frontLeg.setPosition(.62);
            sleep(300);
            rightWinch.setTargetPosition(-1000);
            rightWinch.setPower(.8);
            leftWinch.setTargetPosition(-1000);
            leftWinch.setPower(.8);
            sleep(1000);
            arch.setPosition(.363);
            Actions.runBlocking(drive.actionBuilder(drive.pose)
                    .strafeToConstantHeading(new Vector2d(51.5, 40.19))
                    .build());
            backLeg.setPosition(.76);  // .9 is closed pos
            sleep(50);
            frontLeg.setPosition(.52);
            sleep(300);
            backLeg.setPosition(.85);  // .9 is closed pos
            sleep(50);
            frontLeg.setPosition(.6);
            sleep(50);
            //  hips.setPosition(.18);
            // sleep(200);
            arch.setPosition(.69);
            sleep(50);
            rightWinch.setTargetPosition(-20);
            rightWinch.setPower(.8);
            leftWinch.setTargetPosition(-20);
            leftWinch.setPower(.8);
            // hips.setPosition(.27);
            sleep(300);
            backLeg.setPosition(.76);  // .9 is closed pos
            frontLeg.setPosition(.52);
            sleep(300);




        } else if (zone == 2) {
            Actions.runBlocking(drive.actionBuilder(new Pose2d(-36.00, 63.00, Math.toRadians(90.00)))
                    .strafeToConstantHeading(new Vector2d(-36.10, 32.77))
                    .strafeToConstantHeading(new Vector2d(-32, 60))
                    .strafeToLinearHeading(new Vector2d(50.91, 60), Math.toRadians(180))
                    .build());
            backLeg.setPosition(.82);
            frontLeg.setPosition(.62);
            sleep(300);
            rightWinch.setTargetPosition(-1000);
            rightWinch.setPower(.8);
            leftWinch.setTargetPosition(-1000);
            leftWinch.setPower(.8);
            sleep(1000);
            arch.setPosition(.363);
            Actions.runBlocking(drive.actionBuilder(drive.pose)
                    .strafeToConstantHeading(new Vector2d(51.11, 32.49))
                    .build());
            backLeg.setPosition(.76);  // .9 is closed pos
            sleep(50);
            frontLeg.setPosition(.52);
            sleep(300);
            backLeg.setPosition(.85);  // .9 is closed pos
            sleep(50);
            frontLeg.setPosition(.6);
            sleep(50);
            //  hips.setPosition(.18);
            // sleep(200);
            arch.setPosition(.69);
            sleep(50);
            rightWinch.setTargetPosition(-20);
            rightWinch.setPower(.8);
            leftWinch.setTargetPosition(-20);
            leftWinch.setPower(.8);
            // hips.setPosition(.27);
            sleep(300);
            backLeg.setPosition(.76);  // .9 is closed pos
            frontLeg.setPosition(.52);
            sleep(300);

        } else {
            Actions.runBlocking(drive.actionBuilder(new Pose2d(-36.00, 63.00, Math.toRadians(90.00)))
                    .strafeToConstantHeading(new Vector2d(-47.05, 31.44))
                    .strafeToLinearHeading(new Vector2d(-32, 60), Math.toRadians(180))
                    .strafeToConstantHeading(new Vector2d(50.91, 60))
                    .build());
            backLeg.setPosition(.82);
            frontLeg.setPosition(.62);
            sleep(300);
            rightWinch.setTargetPosition(-1000);
            rightWinch.setPower(.8);
            leftWinch.setTargetPosition(-1000);
            leftWinch.setPower(.8);
            sleep(1000);
            arch.setPosition(.363);
            Actions.runBlocking(drive.actionBuilder(drive.pose)
                    .strafeToLinearHeading(new Vector2d(51.5, 24.49), Math.toRadians(180))
                    .build());
            backLeg.setPosition(.76);  // .9 is closed pos
            sleep(50);
            frontLeg.setPosition(.52);
            sleep(300);
            backLeg.setPosition(.85);  // .9 is closed pos
            sleep(50);
            frontLeg.setPosition(.6);
            sleep(50);
            //  hips.setPosition(.18);
            // sleep(200);
            arch.setPosition(.69);
            sleep(50);
            rightWinch.setTargetPosition(-20);
            rightWinch.setPower(.8);
            leftWinch.setTargetPosition(-20);
            leftWinch.setPower(.8);
            // hips.setPosition(.27);
            sleep(300);
            backLeg.setPosition(.76);  // .9 is closed pos
            frontLeg.setPosition(.52);
            sleep(300);


        }
        drive.updatePoseEstimate();
        telemetry.addLine("Pose" + drive.pose.position);
        telemetry.addLine("HEading" + Math.toDegrees(drive.pose.heading.log()));
        telemetry.update();


        //Drive to Backdrop

        if (zone == 1) {
            Actions.runBlocking(drive.actionBuilder(drive.pose)
                    .strafeToLinearHeading(new Vector2d(49, 60), Math.toRadians(270))
                    .build());
        } else if (zone == 2) {
            Actions.runBlocking(drive.actionBuilder(drive.pose)
                    .strafeToLinearHeading(new Vector2d(49, 60), Math.toRadians(270))
                    .build());


//            Actions.runBlocking(drive.actionBuilder(drive.pose) start
//                    .strafeTo( new Vector2d(28,48))
//                    .build());
//            intake.setPower(0);
//
//            Actions.runBlocking(drive.actionBuilder(drive.pose)
//            .strafeToConstantHeading(new Vector2d(25,26))
//                    .strafeToLinearHeading(new Vector2d(100,26), Math.toRadians(180))
//                    .build());
//
//            backLeg.setPosition(.9);
//            frontLeg.setPosition(.7);
//            sleep(400);
//            rightWinch.setTargetPosition(-800);
//            rightWinch.setPower(.8);
//            leftWinch.setTargetPosition(-800);
//            leftWinch.setPower(.8);
//            arch.setPosition(.363);
//            Actions.runBlocking(drive.actionBuilder(drive.pose)
//                    .strafeToLinearHeading(new Vector2d(133,50),Math.toRadians(180))
//                    .build());
//            backLeg.setPosition(.7);  // .9 is closed pos
//            sleep(200);
//            frontLeg.setPosition(.46);
//            sleep(200);
//            Actions.runBlocking(drive.actionBuilder(drive.pose)
//                    .strafeToConstantHeading(new Vector2d(130,49.5))
//                    .build());
//

        } else
            Actions.runBlocking(drive.actionBuilder(drive.pose)
                    .strafeToLinearHeading(new Vector2d(49, 60), Math.toRadians(270))
                    .build());

        drive.updatePoseEstimate();
        telemetry.addLine("Pose" + drive.pose.position);
        telemetry.addLine("HEading" + Math.toDegrees(drive.pose.heading.log()));
        telemetry.update();
        while(!isStopRequested()){

        }

        if(zone == 1){
            sleep(200);
        }
        else if( zone == 2){
            sleep(200);
        }
        else
            sleep(200);
            //PARK


    }










}