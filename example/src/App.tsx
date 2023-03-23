import React, { useEffect } from 'react';

import { StyleSheet, View, Text, TouchableOpacity } from 'react-native';
import {
  connectDisconnect,
  initSDK,
  getInBodyConnect,
} from 'react-native-inbody-sdk';

export default function App() {
  const [initSucess, setInitSucess] = React.useState(false);
  useEffect(() => {
    initSDKPress();
  }, []);
  const initSDKPress = async () => {
    const result = await initSDK({
      height: 170.6,
      weight: 66.5,
      age: 40,
      gender: 'M',
      deviceName: 'InBodyBand2',
      useHealthKit: false,
    });
    console.log('shaun111', result);
    setInitSucess(result.IsSuccess == 1);
  };
  return (
    <View style={styles.container}>
      <TouchableOpacity
        style={{ padding: 10, backgroundColor: 'green', marginBottom: 20 }}
        onPress={async () => await connectDisconnect('true')}
      >
        <Text>InitSDK</Text>
      </TouchableOpacity>
      {initSucess && (
        <TouchableOpacity
          style={{ padding: 10, backgroundColor: 'red' }}
          onPress={getInBodyConnect(0, 0, 0)}
        >
          <Text>Connect</Text>
        </TouchableOpacity>
      )}
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
    backgroundColor: 'white',
  },
  box: {
    width: 60,
    height: 60,
    marginVertical: 20,
  },
});
