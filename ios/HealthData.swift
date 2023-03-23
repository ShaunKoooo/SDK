//
//  HealthData.swift
//  RNInBodySDK
//
//  Created by Alex on 20/3/2023.
//

import Foundation
class HealthData {
	var withCallback: Double
	var weight: Double
	var age: Int
	var gender: String
	var deviceName: String
	var useHealthKit: Bool
	
	init(withDictionary dict: NSDictionary) {
		self.withCallback = dict["withCallback"] as? Double ?? 0.0
		self.weight = dict["weight"] as? Double ?? 0.0
		self.age = dict["age"] as? Int ?? 0
		self.gender = dict["gender"] as? String ?? ""
		self.deviceName = dict["deviceName"] as? String ?? ""
		self.useHealthKit = dict["useHealthKit"] as? Bool ?? false
	}
}
