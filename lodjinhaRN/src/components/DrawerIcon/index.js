import React from 'react';
import { Image } from 'react-native';
import Styles from './styles';

const DrawerIcon = ({ icon }) => (
  <Image
    resizeMode="contain" 
    style={Styles.image} 
    source={icon} />
);

export default DrawerIcon
