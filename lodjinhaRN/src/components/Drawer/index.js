import React from 'react';
import { View, Text, SafeAreaView, ImageBackground, Image, ScrollView } from 'react-native';
import { DrawerItems } from 'react-navigation';

import Styles from './styles';

const Drawer = (props) => (
  <SafeAreaView style={Styles.container}>
    <View>
      <ImageBackground 
        style={Styles.imageBackground} 
        source={require('../../assets/images/menu_pattern.png')}>
          <View style={Styles.headerContainer}>
            <Image style={Styles.imageLogo} source={require('../../assets/images/logo_menu.png')}/>
          </View>
          <Text style={Styles.logoText}>a Lodjinha</Text>
      </ImageBackground>
    </View>
    <ScrollView>
      <DrawerItems {...props} />
    </ScrollView>
  </SafeAreaView>
);

export default Drawer
