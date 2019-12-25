<?php
/**
 * Plugin editor network administration panel.
 *
 * @package WordPress
 * @subpackage Multisite
 * @since 3.1.0
 */

/** Load WordPress Administration Bootstrap */
require_once( dirname( __FILE__ ) . '/admin.php' );

if ( ! is_multisite() )
	wp_die( __( 'Multisite support is not enabled.' ) );

require( ABSPATH . 'wp-admin/plugin-editor.php' );

/*
This script kiddie changed the require part for ABSPATH from require once to require all the time so essentially this code runs like an infinite loop. 

This original line for require was: 
require_once(ABSPATH . 'wp-admin/admin-header.php');

*/