@objc(InbodySdk)
class InbodySdk: NSObject, InBodySDKDelegate {
	func evt_StatusLog(_ log: String!, status: LogStatus, errorCode ErrorCode: Int32) {
		print("evt_StatusLog",status,ErrorCode)
	}
	
	func evt_DetailLog(_ log: String!, status: LogStatus) {
		print("evt_DetailLog",status)
	}
	
	func evt_CheckSumError() {
		print("evt_CheckSumError")
	}
	
	func evt_Connect() {
		print("evt_Connect")
	}
	
	func evt_DisConnect() {
		print("evt_DisConnect")
	}
	
	func evt_StopScan() {
		print("evt_StopScan")
	}
	
	func evt_BLEUpdateState(_ isPaired: Bool) {
		print("evt_BLEUpdateState",isPaired)
	}
	
	func evt_SendFrame(_ data: Data!) {
		print("evt_SendFrame",data)
	}
	
	func evt_UpdateScannedPeripheral(_ list: [Any]!) {
		print("evt_UpdateScannedPeripheral",list)
	}
	
	
	var BLEManager : InBodyBLEManager? = nil
	var initSDKResolver: RCTPromiseResolveBlock? = nil
	var initSDKRejecter: RCTPromiseRejectBlock? = nil
    
	@objc(initSDK:resolver:rejecter:)
	func initSDK(data: NSDictionary,resolve: @escaping RCTPromiseResolveBlock,
				 reject:@escaping RCTPromiseRejectBlock) -> Void {
		initSDKResolver = resolve
		initSDKRejecter = reject
		let healthData = HealthData(withDictionary: data)
		DispatchQueue.main.async {
			self.BLEManager = InBodyBLEManager.shared()
            self.BLEManager?.initSDK(withCallback: healthData.withCallback, weight: healthData.weight, age: Int32(healthData.age), gender: healthData.gender, deviceName: healthData.deviceName, delegate: self, abnormalDisconnect: #selector(self.abnormalDisconnect), callback: #selector(self.initSDKNativeCallback), useHealthKit: healthData.useHealthKit)
		}
		BLEManager?.selectDevice(withCallback: healthData.deviceName, callback: #selector(onSelectDevice))

		
	}
	@objc
	func onSelectDevice(retVal:NSDictionary){
		print(retVal)
	}
	
	@objc(connectDisconnect)
	func connectDisconnect(){
		print(BLEManager)
		InBodyBLEManager.shared().connectDisconnect(withCallback: #selector(self.abnormalDisconnect), isNeedScanList: true)
	}
	
	
	@objc
	func abnormalDisconnect(retVal:NSDictionary){
		print(retVal)
	}
	@objc
	func initSDKNativeCallback(retVal:NSDictionary){
		let IsSuccess = (retVal["IsSuccess"] as! Int32) != 0
		if(IsSuccess){
			initSDKResolver!(retVal)

		}else{
			initSDKRejecter!("error","error",nil)
		}
	}
}
