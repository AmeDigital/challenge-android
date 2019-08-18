/**
 * @format
 */

import { Text } from 'react-native';
import 'react-native';
import React from 'react';

// Note: test renderer must be required after react-native.
import renderer from 'react-test-renderer';

const MeuComp = <Text>Text</Text>;

it('renders correctly', () => {
	renderer.create(<MeuComp />).toJSON();
});
