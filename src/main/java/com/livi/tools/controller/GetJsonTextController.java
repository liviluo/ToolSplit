package com.livi.tools.controller;

import java.io.IOException;

import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.livi.tools.util.ReadAndWriteJsonUtil;

@RestController
public class GetJsonTextController {

	@RequestMapping(value = "/getJson")
	public JSONObject action(@RequestBody(required = false) JSONObject param) {
		JSONObject result = new JSONObject();
		try {
			result = ReadAndWriteJsonUtil.read("jsonText/json.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
		if (param != null) {
			try {
				ReadAndWriteJsonUtil.write("jsonText/inputJson.txt", param.toString());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}
