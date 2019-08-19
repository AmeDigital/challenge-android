import React from 'react';

import renderer from 'react-test-renderer';

import LogoTitle from '../../src/components/LogoTitle';

describe('Component LogoTitle', () => {
	test('should render without erros', () => {
		const tree = renderer.create(<LogoTitle navigation={{ openDrawer: function() {} }} />).toJSON();
		expect(tree).toMatchSnapshot();
	});
});
