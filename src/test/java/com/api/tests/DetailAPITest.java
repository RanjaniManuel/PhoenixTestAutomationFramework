package com.api.tests;

import static org.hamcrest.Matchers.*;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.api.constants.Role;
import com.api.request.model.Detail;
import com.api.services.DashboardService;
import com.api.utils.SpecUtil;

public class DetailAPITest {
	
	
	private DashboardService dashboardService;
	private Detail detailPayload;
	
	@BeforeTest(description = "Instantiating the Dashboard Service")
	public void setup() {
		dashboardService=new DashboardService();
		detailPayload=new Detail("created_today");
	}
	
	@Test(description = "Verifing if  detail api is working ", groups = {"api", "smoke","regression"})
	public void detailAPITest() {
		/*
		 "id": 149769,
            "job_number": "JOB_149769",
            "tr_customer_id": 149786,
            "tr_customer_product_id": 149776,
            "mst_service_location_id": 1,
            "mst_platform_id": 2,
            "mst_action_status_id": 2,
            "mst_warrenty_status_id": 1,
            "mst_oem_id": 1,
            "repair_start_date": null,
            "repair_end_date": null,
            "created_at": "2026-01-09T01:39:20.000Z",
            "modified_at": "2026-01-09T01:39:20.000Z",
            "name": "steve a",
            "mobile_number": "9900998876",
            "email_id": "steve@gmail.com",
            "dop": "2025-10-06",
            "serial_number": "34880833002750",
            "imei1": "34880833002750",
            "imei2": "34880833002750",
            "popurl": "2025-10-06T18:30:00.000Z",
            "mst_model_id": 1,
            "mst_product_id": 1,
            "mst_model_name": "Nexus 2 blue",
            "mst_product_name": "Nexus 2",
            "mst_service_location_name": "Service Center A",
            "mst_action_status_code": "PJFA",
            "mst_platform_name": "Front Desk",
            "mst_warrenty_status": "In Warrenty",
            "mst_oem_name": "Google",
            "mst_action_status": "Pending For Job Assignment",
            "assigned_by": null,
            "assigned_on": "2026-01-09T01:39:20.000Z",
            "assigned_to": null,
            "mst_warrenty_status_code": "IW"
		 */
		dashboardService.details(Role.FD, detailPayload)
		.then()
		.spec(SpecUtil.responseSpec_OK())
		.body("message", equalTo("Success"))
		.body("data.assigned_by", everyItem(anyOf(nullValue(),instanceOf(Integer.class))))
		.log();
	}

}
