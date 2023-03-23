#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(InbodySdk, NSObject)

RCT_EXTERN_METHOD(initSDK:(NSDictionary)data resolver:(RCTPromiseResolveBlock)resolve
				  rejecter: (RCTPromiseRejectBlock)reject)

RCT_EXTERN_METHOD(connectDisconnect)

+ (BOOL)requiresMainQueueSetup
{
  return YES;
}

@end
