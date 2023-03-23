import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-inbody-sdk' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const InBodySdk = NativeModules.InBodySdk
  ? NativeModules.InBodySdk
  : new Proxy(
    {},
    {
      get() {
        throw new Error(LINKING_ERROR);
      },
    }
  );

export function initSDK(data: {
  height: number;
  weight: number;
  age: number;
  gender: string;
  deviceName: string;
  useHealthKit: boolean;
}) {
  return InBodySdk.initSDK(data);
}

export function connectDisconnect(isConnect: string) {
  return InBodySdk.connectDisconnect(isConnect);
}

export function getInBodyConnect(
  language: number,
  unit: number,
  sound: number
) {
  return InBodySdk.getInBodyConnect(language, unit, sound);
}
