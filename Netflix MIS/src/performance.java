import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JScrollPane;
import javax.swing.JPanel;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class performance {
	
	JFrame frame;
	
	public Font CanvaSans, Inter;
	
	private static JLabel likesCount, commentsCount, sharesCount, promotionCost, subscribersCount, websiteTrafficCount, selectedCampaignLbl, topPerformingCampaign, salesCount, topPerformingPromotion, campaignRemark;
	private static JLabel likesPercentage, commentsPercentage, sharesPercentage, websiteTrafficPercentage, subscribersPercentage, salesPercentage;
	
	JPanel panel, tablePanel;
	
	static int startingYPos = 11, currentYPos = 11, currentYPos2 = 11;
	
	boolean actionAllowed = false;
	
	public static double minValue(double val1, double val2) {
		double minimumValue = 1;
		
		if (val1 == val2) {
			minimumValue = val1;
		} else if (val1 > val2) {
			minimumValue = val2;
		} else if (val1 < val2) {
			minimumValue = val1;
		}
		
		if (minimumValue < 1) {
			minimumValue = 1;
		}
		
		return minimumValue;
	}
	
	public void refresh() {
		currentYPos = startingYPos;
		currentYPos2 = startingYPos;
		panel.removeAll();
		tablePanel.removeAll();
		loadCampaignHistory();
		summarize("240501");
		sortTable(2);
	}
	
	static String campaignRemark(double sales) {
		String remark = "";
		
	    if (sales < -100.0) {
	        campaignRemark.setForeground(new Color(150, 0, 0));
	        try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
					PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM remarks WHERE remark_id = 10")) {
				
				try (ResultSet resultSet = preparedStmt.executeQuery()) {
	                while (resultSet.next()) {
	                	remark = resultSet.getString("remark");
	                }
	            }
			} catch (SQLException f) {
				f.printStackTrace();
			}
	    } else if (sales < -50.0) {
	        campaignRemark.setForeground(new Color(150, 30, 0));
	        try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
					PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM remarks WHERE remark_id = 20")) {
				
				try (ResultSet resultSet = preparedStmt.executeQuery()) {
	                while (resultSet.next()) {
	                	remark = resultSet.getString("remark");
	                }
	            }
			} catch (SQLException f) {
				f.printStackTrace();
			}
	    } else if (sales < 0.0) {
	        campaignRemark.setForeground(new Color(242, 129, 0));
	        try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
					PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM remarks WHERE remark_id = 30")) {
				
				try (ResultSet resultSet = preparedStmt.executeQuery()) {
	                while (resultSet.next()) {
	                	remark = resultSet.getString("remark");
	                }
	            }
			} catch (SQLException f) {
				f.printStackTrace();
			}
	    } else if (sales == 0.0) {
	        campaignRemark.setForeground(new Color(245, 171, 86));
	        try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
					PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM remarks WHERE remark_id = 70")) {
				
				try (ResultSet resultSet = preparedStmt.executeQuery()) {
	                while (resultSet.next()) {
	                	remark = resultSet.getString("remark");
	                }
	            }
			} catch (SQLException f) {
				f.printStackTrace();
			}
	    } else if (sales > 100.0) {
	        campaignRemark.setForeground(new Color(5, 150, 0));
	        try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
					PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM remarks WHERE remark_id = 40")) {
				
				try (ResultSet resultSet = preparedStmt.executeQuery()) {
	                while (resultSet.next()) {
	                	remark = resultSet.getString("remark");
	                }
	            }
			} catch (SQLException f) {
				f.printStackTrace();
			}
	    } else if (sales > 50.0) {
	        campaignRemark.setForeground(new Color(55, 150, 0));
	        try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
					PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM remarks WHERE remark_id = 50")) {
				
				try (ResultSet resultSet = preparedStmt.executeQuery()) {
	                while (resultSet.next()) {
	                	remark = resultSet.getString("remark");
	                }
	            }
			} catch (SQLException f) {
				f.printStackTrace();
			}
	    } else if (sales > 0.0) {
	        campaignRemark.setForeground(new Color(85, 150, 0));
	        try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
					PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM remarks WHERE remark_id = 60")) {
				
				try (ResultSet resultSet = preparedStmt.executeQuery()) {
	                while (resultSet.next()) {
	                	remark = resultSet.getString("remark");
	                }
	            }
			} catch (SQLException f) {
				f.printStackTrace();
			}
	    } else {
	        campaignRemark.setForeground(new Color(245, 171, 86));
	        try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
					PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM remarks WHERE remark_id = 70")) {
				
				try (ResultSet resultSet = preparedStmt.executeQuery()) {
	                while (resultSet.next()) {
	                	remark = resultSet.getString("remark");
	                }
	            }
			} catch (SQLException f) {
				f.printStackTrace();
			}
	    }
	    
	    return remark;
	}
	
	static void summarize(String id) {
		// Campaign Date
		String campaignDate = "";
		
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
				PreparedStatement preparedStmt = conn.prepareStatement("SELECT campaign_startdate FROM campaign WHERE campaign_id = ?")) {
			
			preparedStmt.setString(1, id);
			
			try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                	campaignDate = resultSet.getString("campaign_startdate");
                }
            }
		} catch (SQLException f) {
			f.printStackTrace();
		}
		
		// Campaign Label
		selectedCampaignLbl.setText("Campaign #" + id);
		
		// Likes
		double currentLikes = 0.0;
		double previousLikes = 0.0;
		
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
				PreparedStatement preparedStmt = conn.prepareStatement("SELECT SUM(engagement_likes) FROM social_media_engagement WHERE campaign_id = ?")) {
			
			preparedStmt.setString(1, id);
			
			try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                	likesCount.setText(resultSet.getString("SUM(engagement_likes)"));
                	currentLikes = resultSet.getInt("SUM(engagement_likes)");
                }
            }
		} catch (SQLException f) {
			f.printStackTrace();
		}
		
		// Likes Percentage
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
				PreparedStatement preparedStmt = conn.prepareStatement("SELECT SUM(engagement_likes) FROM social_media_engagement WHERE engagement_date >= DATE_SUB(?, INTERVAL 30 DAY) && engagement_date < ?")) {
			
			preparedStmt.setString(1, campaignDate);
			preparedStmt.setString(2, campaignDate);
			
			try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                	previousLikes = resultSet.getInt("SUM(engagement_likes)");
                	
                	double percentage = ((currentLikes - previousLikes) / minValue(currentLikes, previousLikes)) * 100;
                	
                	likesPercentage.setText(String.format("%.0f", percentage) + "%");
                	
                	if (percentage < 1.0) {
                		likesPercentage.setForeground(new Color(175, 31, 36));
                	} else {
                		likesPercentage.setForeground(new Color(3, 137, 56));
                	}
                }
            }
		} catch (SQLException f) {
			f.printStackTrace();
		}
		
		// Comments
		double currentComments = 0.0;
		double previousComments = 0.0;
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
				PreparedStatement preparedStmt = conn.prepareStatement("SELECT SUM(engagement_comments) FROM social_media_engagement WHERE campaign_id = ?")) {
			
			preparedStmt.setString(1, id);
			
			try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                	commentsCount.setText(resultSet.getString("SUM(engagement_comments)"));
                	currentComments = resultSet.getInt("SUM(engagement_comments)");
                }
            }
		} catch (SQLException f) {
			f.printStackTrace();
		}
		
		// Comments Percentage
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
				PreparedStatement preparedStmt = conn.prepareStatement("SELECT SUM(engagement_comments) FROM social_media_engagement WHERE engagement_date >= DATE_SUB(?, INTERVAL 30 DAY) && engagement_date < ?")) {
			
			preparedStmt.setString(1, campaignDate);
			preparedStmt.setString(2, campaignDate);
			
			try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                	previousComments = resultSet.getInt("SUM(engagement_comments)");
                	
                	double percentage = ((currentComments - previousComments) / minValue(currentComments, previousComments)) * 100;
                	
                	commentsPercentage.setText(String.format("%.0f", percentage) + "%");
                	
                	if (percentage < 1.0) {
                		commentsPercentage.setForeground(new Color(175, 31, 36));
                	} else {
                		commentsPercentage.setForeground(new Color(3, 137, 56));
                	}
                }
            }
		} catch (SQLException f) {
			f.printStackTrace();
		}
		
		// Shares
		double currentShares = 0.0;
		double previousShares = 0.0;
		
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
				PreparedStatement preparedStmt = conn.prepareStatement("SELECT SUM(engagement_shares) FROM social_media_engagement WHERE campaign_id = ?")) {
			
			preparedStmt.setString(1, id);
			
			try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                	sharesCount.setText(resultSet.getString("SUM(engagement_shares)"));
                	currentShares = resultSet.getInt("SUM(engagement_shares)");
                }
            }
		} catch (SQLException f) {
			f.printStackTrace();
		}
		
		// Shares Percentage
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
				PreparedStatement preparedStmt = conn.prepareStatement("SELECT SUM(engagement_shares) FROM social_media_engagement WHERE engagement_date >= DATE_SUB(?, INTERVAL 30 DAY) && engagement_date < ?")) {
			
			preparedStmt.setString(1, campaignDate);
			preparedStmt.setString(2, campaignDate);
			
			try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                	previousShares= resultSet.getInt("SUM(engagement_shares)");
                	
                	double percentage = ((currentShares- previousShares) / minValue(currentShares, previousShares)) * 100;
                	
                	sharesPercentage.setText(String.format("%.0f", percentage) + "%");
                	
                	if (percentage < 1.0) {
                		sharesPercentage.setForeground(new Color(175, 31, 36));
                	} else {
                		sharesPercentage.setForeground(new Color(3, 137, 56));
                	}
                }
            }
		} catch (SQLException f) {
			f.printStackTrace();
		}
		
		// Campaign Cost
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
				PreparedStatement preparedStmt = conn.prepareStatement("SELECT * FROM campaign WHERE campaign_id = ?")) {
			
			preparedStmt.setString(1, id);
			
			try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                	String cost = resultSet.getString("campaign_cost");
                	double amount = Double.parseDouble(cost);
                	DecimalFormat formatter = new DecimalFormat("#,###.00");
                	promotionCost.setText("P" + formatter.format(amount));
                }
            }
		} catch (SQLException f) {
			f.printStackTrace();
		}
		
		// Subscribers
		double currentSubscribers = 0.0;
		double previousSubscribers = 0.0;
		
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
				PreparedStatement preparedStmt = conn.prepareStatement("SELECT COUNT(sales_id) FROM sales_data WHERE campaign_id = ?")) {
			
			preparedStmt.setString(1, id);
			
			try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                	subscribersCount.setText(Integer.toString(resultSet.getInt("COUNT(sales_id)")));
                	currentSubscribers = resultSet.getInt("COUNT(sales_id)");
                }
            }
		} catch (SQLException f) {
			f.printStackTrace();
		}
		
		// Subsribers Percentage
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
				PreparedStatement preparedStmt = conn.prepareStatement("SELECT COUNT(sales_id) FROM sales_data WHERE sales_date >= DATE_SUB(?, INTERVAL 30 DAY) && sales_date < ?")) {
			
			preparedStmt.setString(1, campaignDate);
			preparedStmt.setString(2, campaignDate);
			
			try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                	previousSubscribers = resultSet.getInt("COUNT(sales_id)");
                	
                	double percentage = ((currentSubscribers - previousSubscribers) / minValue(currentSubscribers, previousSubscribers)) * 100;
                	
                	subscribersPercentage.setText(String.format("%.0f", percentage) + "%");
                	
                	if (percentage < 1.0) {
                		subscribersPercentage.setForeground(new Color(175, 31, 36));
                	} else {
                		subscribersPercentage.setForeground(new Color(3, 137, 56));
                	}
                }
            }
		} catch (SQLException f) {
			f.printStackTrace();
		}
		
		// Website Traffic
		double currentWebTraffic = 0.0;
		double previousWebTraffic = 0.0;
		
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
				PreparedStatement preparedStmt = conn.prepareStatement("SELECT COUNT(traffic_id) FROM website_traffic WHERE campaign_id = ?")) {
			
			preparedStmt.setString(1, id);
			
			try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                	websiteTrafficCount.setText(Integer.toString(resultSet.getInt("COUNT(traffic_id)")));
                	currentWebTraffic = resultSet.getInt("COUNT(traffic_id)");
                }
            }
		} catch (SQLException f) {
			f.printStackTrace();
		}
		
		// Website Traffic Percentage
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
				PreparedStatement preparedStmt = conn.prepareStatement("SELECT COUNT(traffic_id) FROM website_traffic WHERE traffic_date >= DATE_SUB(?, INTERVAL 30 DAY) && traffic_date < ?")) {
			
			preparedStmt.setString(1, campaignDate);
			preparedStmt.setString(2, campaignDate);
			
			try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                	previousWebTraffic= resultSet.getInt("COUNT(traffic_id)");
                	
                	double percentage = ((currentWebTraffic - previousWebTraffic) / minValue(currentWebTraffic, previousWebTraffic)) * 100;
                	
                	websiteTrafficPercentage.setText(String.format("%.0f", percentage) + "%");
                	
                	if (percentage < 1.0) {
                		websiteTrafficPercentage.setForeground(new Color(175, 31, 36));
                	} else {
                		websiteTrafficPercentage.setForeground(new Color(3, 137, 56));
                	}
                }
            }
		} catch (SQLException f) {
			f.printStackTrace();
		}
		
		// Gross Income
		double currentIncome = 0.0;
		double previousIncome = 0.0;
		
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
				PreparedStatement preparedStmt = conn.prepareStatement("SELECT SUM(sales_amount) FROM sales_data WHERE campaign_id = ?")) {
			
			preparedStmt.setString(1, id);
			
			try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                	String cost = resultSet.getString("SUM(sales_amount)");
                	double amount = Double.parseDouble(cost);
                	DecimalFormat formatter = new DecimalFormat("#,###.00");
                	salesCount.setText("P" + formatter.format(amount));
                	
                	currentIncome = resultSet.getInt("SUM(sales_amount)");
                }
            }
		} catch (SQLException f) {
			f.printStackTrace();
		}
		
		// Gross Income Percentage
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
				PreparedStatement preparedStmt = conn.prepareStatement("SELECT SUM(sales_amount) FROM sales_data WHERE sales_date >= DATE_SUB(?, INTERVAL 30 DAY) && sales_date < ?")) {
			
			preparedStmt.setString(1, campaignDate);
			preparedStmt.setString(2, campaignDate);
			
			try (ResultSet resultSet = preparedStmt.executeQuery()) {
                while (resultSet.next()) {
                	previousIncome = resultSet.getInt("SUM(sales_amount)");
                	
                	double percentage = ((currentIncome - previousIncome) / minValue(currentIncome, previousIncome)) * 100;
                	
                	salesPercentage.setText(String.format("%.0f", percentage) + "%");
                	campaignRemark.setText(campaignRemark(percentage));
                	
                	if (percentage < 1.0) {
                		salesPercentage.setForeground(new Color(175, 31, 36));
                	} else {
                		salesPercentage.setForeground(new Color(3, 137, 56));
                	}
                }
            }
		} catch (SQLException f) {
			f.printStackTrace();
		}
	}
	
	void loadCampaignHistory() {
		// Campaign History
		try {
			Inter = Font.createFont(Font.TRUETYPE_FONT, new File("Inter-Medium.otf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("FontsFree-Net-norwester.ttf")));
		} catch(IOException | FontFormatException e) {}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM campaign");
			
			int topPerformingSales = 0;
			String topPerformingCam = "";
			
			while (rs.next()) {
				String id = rs.getString("campaign_id");
				JLabel campaignHistory = new JLabel("Campaign ID #" + id);
				campaignHistory.addMouseListener(new MouseAdapter() {
					@Override
					public void mousePressed(MouseEvent e) {
						campaignHistory.setForeground(new Color(255, 31, 36));
					}
					@Override
					public void mouseReleased(MouseEvent e) {
						campaignHistory.setForeground(new Color(175, 31, 36));
						summarize(id);
					}
					@Override
					public void mouseEntered(MouseEvent e) {
						campaignHistory.setForeground(new Color(175, 31, 36));
					}
					@Override
					public void mouseExited(MouseEvent e) {
						campaignHistory.setForeground(new Color(0, 0, 0));
					}
				});
				campaignHistory.setBounds(18, currentYPos, 296, 27);
				campaignHistory.setFont(Inter.deriveFont(17f));
				panel.add(campaignHistory);
				
				boolean bySalesAmount = false;
				
				if (bySalesAmount) {
					try (PreparedStatement preparedStmt = conn.prepareStatement("SELECT SUM(sales_amount) FROM sales_data WHERE campaign_id = ?")) {
						preparedStmt.setString(1, id);
						try (ResultSet resultSet = preparedStmt.executeQuery()) {
			                while (resultSet.next()) {
			                	if (resultSet.getInt("SUM(sales_amount)") > topPerformingSales) {
			                		topPerformingSales = resultSet.getInt("SUM(sales_amount)");
			                		topPerformingCam = id;
			                	}
			                }
			            }
					} catch (SQLException f) {
						f.printStackTrace();
					}
					
					topPerformingCampaign.setText("Campaign #" + topPerformingCam);
					
					try (PreparedStatement preparedStmt = conn.prepareStatement("SELECT campaign_type FROM campaign WHERE campaign_id = ?")) {
						preparedStmt.setString(1, topPerformingCam);
						try (ResultSet resultSet = preparedStmt.executeQuery()) {
			                while (resultSet.next()) {
			                	topPerformingPromotion.setText(resultSet.getString("campaign_type"));
			                }
			            }
					} catch (SQLException f) {
						f.printStackTrace();
					}
					
				} else {
					double highestPercentage = 0.0;
					String highestCampaignID = "";
					double currentPercentage = 0.0;
					double currentIncome = 0.0;
					double previousIncome = 0.0;
					
					// Campaign Date
					String campaignDate = "";
					try (Connection conn2 = DriverManager.getConnection(app.url, app.user, app.password);
							PreparedStatement preparedStmt = conn2.prepareStatement("SELECT campaign_startdate FROM campaign WHERE campaign_id = ?")) {
						
						preparedStmt.setString(1, id);
						
						try (ResultSet resultSet = preparedStmt.executeQuery()) {
			                while (resultSet.next()) {
			                	campaignDate = resultSet.getString("campaign_startdate");
			                }
			            }
					} catch (SQLException f) {
						f.printStackTrace();
					}
					
					// Current Income
					try (Connection conn2 = DriverManager.getConnection(app.url, app.user, app.password);
							PreparedStatement preparedStmt = conn2.prepareStatement("SELECT SUM(sales_amount) FROM sales_data WHERE campaign_id = ?")) {
						
						preparedStmt.setString(1, id);
						
						try (ResultSet resultSet = preparedStmt.executeQuery()) {
			                while (resultSet.next()) {
			                	
			                	currentIncome = resultSet.getInt("SUM(sales_amount)");
			                }
			            }
					} catch (SQLException f) {
						f.printStackTrace();
					}
					
					// Previous Income
					try (Connection conn2 = DriverManager.getConnection(app.url, app.user, app.password);
							PreparedStatement preparedStmt = conn2.prepareStatement("SELECT SUM(sales_amount) FROM sales_data WHERE sales_date >= DATE_SUB(?, INTERVAL 30 DAY) && sales_date < ?")) {
						
						preparedStmt.setString(1, campaignDate);
						preparedStmt.setString(2, campaignDate);
						
						try (ResultSet resultSet = preparedStmt.executeQuery()) {
			                while (resultSet.next()) {
			                	previousIncome = resultSet.getInt("SUM(sales_amount)");
			                	
			                	currentPercentage = ((currentIncome - previousIncome) / minValue(currentIncome, previousIncome)) * 100;
			                }
			            }
					} catch (SQLException f) {
						f.printStackTrace();
					}
					
					if (currentPercentage > highestPercentage) {
						highestPercentage = currentPercentage;
						highestCampaignID = id;

						topPerformingCampaign.setText("Campaign #" + highestCampaignID);
					}
					
					try (PreparedStatement preparedStmt = conn.prepareStatement("SELECT campaign_type FROM campaign WHERE campaign_id = ?")) {
						preparedStmt.setString(1, highestCampaignID);
						try (ResultSet resultSet = preparedStmt.executeQuery()) {
			                while (resultSet.next()) {
			                	topPerformingPromotion.setText(resultSet.getString("campaign_type"));
			                }
			            }
					} catch (SQLException f) {
						f.printStackTrace();
					}
				}
				
				currentYPos += 30;
				panel.revalidate();
				panel.repaint();
				panel.setPreferredSize(new Dimension(915, currentYPos + 11));
			
			}
			
			rs.close();
			stmt.close();
			conn.close();
		}  catch (ClassNotFoundException f) {
			System.err.println("Could not load JDBC driver: " + f.getMessage());
		} catch (SQLException f) {
			System.err.println("SQL Exception: " + f.getMessage());
		}
	}
	
	void loadTable(String sort) {
		try (Connection conn = DriverManager.getConnection(app.url, app.user, app.password);
			     PreparedStatement preparedStmt = conn.prepareStatement(
			    		 "SELECT DATE_FORMAT(c.campaign_startdate, '%Y-%m') AS month, " +
			        		 "COUNT(DISTINCT sme.engagement_id) AS engagement_count, " +
			        		 "COUNT(DISTINCT wt.traffic_id) AS traffic_count, " +
			        		 "SUM(DISTINCT campaign_cost) AS cost_count, " +
			        		 "COUNT(DISTINCT s.sales_id) AS subs_count, " +
			        		 "SUM(s.sales_amount) AS sales_count " +
			    		 "FROM campaign c " +
			    		 "LEFT JOIN social_media_engagement sme ON DATE_FORMAT(c.campaign_startdate, '%Y-%m') = DATE_FORMAT(sme.engagement_date, '%Y-%m') " +
			    		 "LEFT JOIN website_traffic wt ON DATE_FORMAT(c.campaign_startdate, '%Y-%m') = DATE_FORMAT(wt.traffic_date, '%Y-%m') " +
			    		 "LEFT JOIN sales_data s ON DATE_FORMAT(c.campaign_startdate, '%Y-%m') = DATE_FORMAT(s.sales_date, '%Y-%m') " +
			    		 "GROUP BY DATE_FORMAT(c.campaign_startdate, '%Y-%m') " +
			    		 sort)) {
			
		    try (ResultSet resultSet = preparedStmt.executeQuery()) {
              while (resultSet.next()) {
              	
              	JLabel tableDate = new JLabel("");
          		tableDate.setHorizontalAlignment(SwingConstants.CENTER);
          		tableDate.setBounds(0, currentYPos2, 93, 28);
          		tableDate.setText(resultSet.getString("month"));
          		tableDate.setFont(Inter.deriveFont(15f));
          		tablePanel.add(tableDate);
          		
          		JLabel tableEngagement = new JLabel("ENGAGEMENT");
          		tableEngagement.setHorizontalAlignment(SwingConstants.CENTER);
          		tableEngagement.setBounds(92, currentYPos2, 140, 28);
          		tableEngagement.setText(resultSet.getString("engagement_count"));
          		tableEngagement.setFont(Inter.deriveFont(15f));
          		tablePanel.add(tableEngagement);
          		
          		JLabel tableWebTraffic = new JLabel("");
	       		tableWebTraffic.setHorizontalAlignment(SwingConstants.CENTER);
	       		tableWebTraffic.setBounds(231, currentYPos2, 146, 28);
	       		tableWebTraffic.setText(resultSet.getString("traffic_count"));
	       		tableWebTraffic.setFont(Inter.deriveFont(15f));
	       		tablePanel.add(tableWebTraffic);
	       		
	       		JLabel tableCost = new JLabel("COST");
	      		tableCost.setHorizontalAlignment(SwingConstants.CENTER);
	      		tableCost.setBounds(377, currentYPos2, 112, 28);
	      		tableCost.setText(resultSet.getString("cost_count"));
	      		tableCost.setFont(Inter.deriveFont(15f));
	      		tablePanel.add(tableCost);
	      		
	      		JLabel tableSubscribers = new JLabel("SUBSCRIBERS");
        		tableSubscribers.setHorizontalAlignment(SwingConstants.CENTER);
        		tableSubscribers.setBounds(488, currentYPos2, 126, 28);
        		tableSubscribers.setText(resultSet.getString("subs_count"));
        		tableSubscribers.setFont(Inter.deriveFont(15f));
        		tablePanel.add(tableSubscribers);
           	
        		JLabel tableSales = new JLabel("SALES");
        		tableSales.setHorizontalAlignment(SwingConstants.CENTER);
        		tableSales.setBounds(614, currentYPos2, 206, 28);
        		DecimalFormat formatter = new DecimalFormat("#,###.00");
        		tableSales.setText("P" + formatter.format(Double.parseDouble(resultSet.getString("sales_count")) / 1600.0));
        		tableSales.setFont(Inter.deriveFont(15f));
        		tablePanel.add(tableSales);
          		
          		currentYPos2 += 30;
          		tablePanel.revalidate();
  				tablePanel.repaint();
  				tablePanel.setPreferredSize(new Dimension(915, currentYPos + 11));
              }
          }
		} catch (SQLException f) {
			System.err.println("SQL Exception: " + f.getMessage());
		}
	}
	
	public void sortTable(int sortID) {
		tablePanel.removeAll();
		currentYPos2 = startingYPos;
		
		switch (sortID) {
			case 0:
				loadTable("ORDER BY cost_count ASC");
				break;
			case 1:
				loadTable("ORDER BY cost_count DESC");
				break;
			case 2:
				loadTable("ORDER BY DATE_FORMAT(c.campaign_startdate, '%Y-%m')");
				break;
			case 3:
				loadTable("ORDER BY DATE_FORMAT(c.campaign_startdate, '%Y-%m') DESC");
				break;
			case 4:
				loadTable("ORDER BY engagement_count ASC");
				break;
			case 5:
				loadTable("ORDER BY engagement_count DESC");
				break;
			case 6:
				loadTable("ORDER BY sales_count ASC");
				break;
			case 7:
				loadTable("ORDER BY sales_count DESC");
				break;
			case 8:
				loadTable("ORDER BY subs_count ASC");
				break;
			case 9:
				loadTable("ORDER BY subs_count DESC");
				break;
			case 10:
				loadTable("ORDER BY traffic_count ASC");
				break;
			case 11:
				loadTable("ORDER BY traffic_count DESC");
				break;
			default:
				loadTable("ORDER BY DATE_FORMAT(c.campaign_startdate, '%Y-%m')");
				break;
		}
	}

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					performance window = new performance();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public performance() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void initialize() {
		
		frame = new JFrame();
		frame.setBounds(100, 100, app.screenWidth + 14, app.screenHeight + 37);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setLocationRelativeTo(null);
		frame.setTitle("Netflix MIS - Insight");
		
		// ================================================================ // Fonts \\ ================================================================ \\
		
		try {
			CanvaSans = Font.createFont(Font.TRUETYPE_FONT, new File("Sans.ttf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("FontsFree-Net-norwester.ttf")));
		} catch(IOException | FontFormatException e) {}

		try {
			Inter = Font.createFont(Font.TRUETYPE_FONT, new File("Inter-Medium.otf"));
			GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
			ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("FontsFree-Net-norwester.ttf")));
		} catch(IOException | FontFormatException e) {}
		
		// =============================================================== // Contents \\ =============================================================== \\
		
		// =============== // Sales \\ =============== \\
		salesCount = new JLabel("3,654,890");
		salesCount.setHorizontalAlignment(SwingConstants.LEFT);
		salesCount.setForeground(new Color(175, 31, 36));
		salesCount.setFont(Inter.deriveFont(Font.BOLD, 18f));
		salesCount.setBounds(535, 398, 113, 21);
		frame.getContentPane().add(salesCount);
		
		salesPercentage = new JLabel("+176%");
		salesPercentage.setForeground(new Color(3, 137, 56));
		salesPercentage.setFont(Inter.deriveFont(10f));
		salesPercentage.setBounds(535, 416, 40, 21);
		frame.getContentPane().add(salesPercentage);
		
		JLabel salesPercentageLbl = new JLabel("compared to last month");
		salesPercentageLbl.setForeground(new Color(155, 155, 155));
		salesPercentageLbl.setFont(Inter.deriveFont(10f));
		salesPercentageLbl.setBounds(571, 416, 140, 21);
		frame.getContentPane().add(salesPercentageLbl);
		
		JLabel salesLbl = new JLabel("Gross Income");
		salesLbl.setHorizontalAlignment(SwingConstants.LEFT);
		salesLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		salesLbl.setBounds(535, 373, 113, 21);
		frame.getContentPane().add(salesLbl);
		
		// =========== // Campaign History \\ =========== \\
		JLabel campaignHistoryLbl = new JLabel("Campaign History");
		campaignHistoryLbl.setHorizontalAlignment(SwingConstants.LEFT);
		campaignHistoryLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		campaignHistoryLbl.setBounds(771, 263, 218, 21);
		frame.getContentPane().add(campaignHistoryLbl);
		
		JScrollPane campaignHistoryScrollPane = new JScrollPane();
		campaignHistoryScrollPane.setBounds(754, 280, 316, 157);
		campaignHistoryScrollPane.setOpaque(false);
		campaignHistoryScrollPane.getViewport().setOpaque(false);
		frame.getContentPane().setLayout(null);
		campaignHistoryScrollPane.setBorder(BorderFactory.createEmptyBorder());
		campaignHistoryScrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
		campaignHistoryScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		campaignHistoryScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(campaignHistoryScrollPane);
		
		panel = new JPanel();
		campaignHistoryScrollPane.setViewportView(panel);
		panel.setOpaque(false);
		panel.setLayout(null);
		
		// ========== // Campaign Selection \\ ========== \\
		selectedCampaignLbl = new JLabel("Current Campaign");
		selectedCampaignLbl.setHorizontalAlignment(SwingConstants.CENTER);
		selectedCampaignLbl.setFont(Inter.deriveFont(12f));
		selectedCampaignLbl.setBounds(250, 54, 140, 21);
		frame.getContentPane().add(selectedCampaignLbl);
		
		// ============== // Engagement \\ =============== \\
		JLabel engagementLbl = new JLabel("Engagements");
		engagementLbl.setHorizontalAlignment(SwingConstants.LEFT);
		engagementLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		engagementLbl.setBounds(268, 91, 93, 21);
		frame.getContentPane().add(engagementLbl);
		
		// Likes
		JLabel likesLbl = new JLabel("Likes");
		likesLbl.setHorizontalAlignment(SwingConstants.LEFT);
		likesLbl.setFont(Inter.deriveFont(12f));
		likesLbl.setBounds(268, 123, 65, 21);
		frame.getContentPane().add(likesLbl);
		
		likesCount = new JLabel("2,365");
		likesCount.setForeground(new Color(175, 31, 36));
		likesCount.setHorizontalAlignment(SwingConstants.LEFT);
		likesCount.setFont(Inter.deriveFont(Font.BOLD, 18f));
		likesCount.setBounds(268, 143, 79, 21);
		frame.getContentPane().add(likesCount);
		
		likesPercentage = new JLabel("+126%");
		likesPercentage.setForeground(new Color(3, 137, 56));
		likesPercentage.setFont(Inter.deriveFont(10f));
		likesPercentage.setBounds(268, 161, 40, 21);
		frame.getContentPane().add(likesPercentage);
		
		JLabel likesPercentageLbl = new JLabel("compared to last month");
		likesPercentageLbl.setForeground(new Color(155, 155, 155));
		likesPercentageLbl.setFont(Inter.deriveFont(10f));
		likesPercentageLbl.setBounds(304, 161, 140, 21);
		frame.getContentPane().add(likesPercentageLbl);
		
		// Comments
		JLabel commentsLbl = new JLabel("Comments");
		commentsLbl.setHorizontalAlignment(SwingConstants.LEFT);
		commentsLbl.setFont(Inter.deriveFont(12f));
		commentsLbl.setBounds(268, 193, 65, 21);
		frame.getContentPane().add(commentsLbl);
		
		commentsCount = new JLabel("5,378");
		commentsCount.setHorizontalAlignment(SwingConstants.LEFT);
		commentsCount.setForeground(new Color(175, 31, 36));
		commentsCount.setFont(Inter.deriveFont(Font.BOLD, 18f));
		commentsCount.setBounds(268, 213, 79, 21);
		frame.getContentPane().add(commentsCount);
		
		commentsPercentage = new JLabel("+300%");
		commentsPercentage.setForeground(new Color(3, 137, 56));
		commentsPercentage.setFont(Inter.deriveFont(10f));
		commentsPercentage.setBounds(268, 231, 40, 21);
		frame.getContentPane().add(commentsPercentage);
		
		JLabel commentsPercentageLbl = new JLabel("compared to last month");
		commentsPercentageLbl.setForeground(new Color(155, 155, 155));
		commentsPercentageLbl.setFont(Inter.deriveFont(10f));
		commentsPercentageLbl.setBounds(304, 231, 140, 21);
		frame.getContentPane().add(commentsPercentageLbl);
		
		// Shares
		JLabel sharesLbl = new JLabel("Shares");
		sharesLbl.setHorizontalAlignment(SwingConstants.LEFT);
		sharesLbl.setFont(Inter.deriveFont(12f));
		sharesLbl.setBounds(268, 268, 65, 21);
		frame.getContentPane().add(sharesLbl);
		
		sharesCount = new JLabel("987");
		sharesCount.setHorizontalAlignment(SwingConstants.LEFT);
		sharesCount.setForeground(new Color(175, 31, 36));
		sharesCount.setFont(Inter.deriveFont(Font.BOLD, 18f));
		sharesCount.setBounds(268, 288, 79, 21);
		frame.getContentPane().add(sharesCount);
		
		sharesPercentage = new JLabel("+20%");
		sharesPercentage.setForeground(new Color(3, 137, 56));
		sharesPercentage.setFont(Inter.deriveFont(10f));
		sharesPercentage.setBounds(268, 306, 40, 21);
		frame.getContentPane().add(sharesPercentage);
		
		JLabel sharesPercentageLbl = new JLabel("compared to last month");
		sharesPercentageLbl.setForeground(new Color(155, 155, 155));
		sharesPercentageLbl.setFont(Inter.deriveFont(10f));
		sharesPercentageLbl.setBounds(304, 306, 140, 21);
		frame.getContentPane().add(sharesPercentageLbl);
		
		// ================== // Promotion Cost \\ ================== \\
		JLabel promotionCostLbl = new JLabel("Promotion Cost");
		promotionCostLbl.setHorizontalAlignment(SwingConstants.LEFT);
		promotionCostLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		promotionCostLbl.setBounds(268, 373, 142, 21);
		frame.getContentPane().add(promotionCostLbl);
		
		promotionCost = new JLabel("P400,000");
		promotionCost.setHorizontalAlignment(SwingConstants.LEFT);
		promotionCost.setForeground(new Color(175, 31, 36));
		promotionCost.setFont(Inter.deriveFont(Font.BOLD, 18f));
		promotionCost.setBounds(268, 398, 222, 21);
		frame.getContentPane().add(promotionCost);
		
		JLabel promotionCostLbl_2 = new JLabel("Cost");
		promotionCostLbl_2.setForeground(new Color(3, 137, 56));
		promotionCostLbl_2.setFont(Inter.deriveFont(10f));
		promotionCostLbl_2.setBounds(268, 416, 40, 21);
		frame.getContentPane().add(promotionCostLbl_2);
		
		JLabel promotionCostLbl_3 = new JLabel("for this campaign");
		promotionCostLbl_3.setForeground(new Color(155, 155, 155));
		promotionCostLbl_3.setFont(Inter.deriveFont(10f));
		promotionCostLbl_3.setBounds(296, 416, 140, 21);
		frame.getContentPane().add(promotionCostLbl_3);
		
		// ============== // Top Performing Promotion \\ =============== \\
		JLabel topPerformingPromotionLbl = new JLabel("Top Performing Promotion");
		topPerformingPromotionLbl.setHorizontalAlignment(SwingConstants.LEFT);
		topPerformingPromotionLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		topPerformingPromotionLbl.setBounds(771, 200, 218, 21);
		frame.getContentPane().add(topPerformingPromotionLbl);
		
		topPerformingPromotion = new JLabel("Video Advertisements");
		topPerformingPromotion.setHorizontalAlignment(SwingConstants.LEFT);
		topPerformingPromotion.setForeground(new Color(175, 31, 36));
		topPerformingPromotion.setFont(Inter.deriveFont(Font.BOLD, 12f));
		topPerformingPromotion.setBounds(772, 218, 264, 21);
		frame.getContentPane().add(topPerformingPromotion);
		
		// =================== // Website Traffic \\ =================== \\
		JLabel websiteTrafficLbl = new JLabel("Website Traffic");
		websiteTrafficLbl.setHorizontalAlignment(SwingConstants.LEFT);
		websiteTrafficLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		websiteTrafficLbl.setBounds(535, 164, 113, 21);
		frame.getContentPane().add(websiteTrafficLbl);
		
		websiteTrafficCount = new JLabel("453,678");
		websiteTrafficCount.setHorizontalAlignment(SwingConstants.LEFT);
		websiteTrafficCount.setForeground(new Color(175, 31, 36));
		websiteTrafficCount.setFont(Inter.deriveFont(Font.BOLD, 18f));
		websiteTrafficCount.setBounds(535, 195, 79, 21);
		frame.getContentPane().add(websiteTrafficCount);
		
		websiteTrafficPercentage = new JLabel("+96%");
		websiteTrafficPercentage.setForeground(new Color(3, 137, 56));
		websiteTrafficPercentage.setFont(Inter.deriveFont(10f));
		websiteTrafficPercentage.setBounds(535, 213, 40, 21);
		frame.getContentPane().add(websiteTrafficPercentage);
		
		JLabel websiteTrafficPercentageLbl = new JLabel("compared to last month");
		websiteTrafficPercentageLbl.setForeground(new Color(155, 155, 155));
		websiteTrafficPercentageLbl.setFont(Inter.deriveFont(10f));
		websiteTrafficPercentageLbl.setBounds(571, 213, 140, 21);
		frame.getContentPane().add(websiteTrafficPercentageLbl);
		
		// =================== // Subscribers \\ =================== \\
		JLabel subscribersLbl = new JLabel("Subscribers");
		subscribersLbl.setHorizontalAlignment(SwingConstants.LEFT);
		subscribersLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		subscribersLbl.setBounds(535, 304, 113, 21);
		frame.getContentPane().add(subscribersLbl);
		
		subscribersCount = new JLabel("3,654,890");
		subscribersCount.setHorizontalAlignment(SwingConstants.LEFT);
		subscribersCount.setForeground(new Color(175, 31, 36));
		subscribersCount.setFont(Inter.deriveFont(Font.BOLD, 18f));
		subscribersCount.setBounds(535, 329, 113, 21);
		frame.getContentPane().add(subscribersCount);
		
		subscribersPercentage = new JLabel("+176%");
		subscribersPercentage.setForeground(new Color(3, 137, 56));
		subscribersPercentage.setFont(Inter.deriveFont(10f));
		subscribersPercentage.setBounds(535, 347, 40, 21);
		frame.getContentPane().add(subscribersPercentage);
		
		JLabel subribersPercentageLbl = new JLabel("compared to last month");
		subribersPercentageLbl.setForeground(new Color(155, 155, 155));
		subribersPercentageLbl.setFont(Inter.deriveFont(10f));
		subribersPercentageLbl.setBounds(571, 347, 140, 21);
		frame.getContentPane().add(subribersPercentageLbl);
		
		// ================= // Campaign Remark \\ ================= \\
		JLabel campaignRemarkLbl = new JLabel("Campaign Remark");
		campaignRemarkLbl.setHorizontalAlignment(SwingConstants.LEFT);
		campaignRemarkLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		campaignRemarkLbl.setBounds(535, 91, 218, 21);
		frame.getContentPane().add(campaignRemarkLbl);
		
		campaignRemark = new JLabel("Very Effective");
		campaignRemark.setHorizontalAlignment(SwingConstants.CENTER);
		campaignRemark.setForeground(new Color(3, 137, 56));
		campaignRemark.setFont(Inter.deriveFont(Font.BOLD, 22f));
		campaignRemark.setBounds(620, 83, 451, 56);
		frame.getContentPane().add(campaignRemark);
		
		// =================== // Top Performing Campaign \\ =================== \\
		JLabel topPerformingCampaignLbl = new JLabel("Top Performing Campaign");
		topPerformingCampaignLbl.setHorizontalAlignment(SwingConstants.LEFT);
		topPerformingCampaignLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		topPerformingCampaignLbl.setBounds(771, 157, 218, 21);
		frame.getContentPane().add(topPerformingCampaignLbl);
		
		topPerformingCampaign = new JLabel("Campaign ID #12345");
		topPerformingCampaign.setHorizontalAlignment(SwingConstants.LEFT);
		topPerformingCampaign.setForeground(new Color(175, 31, 36));
		topPerformingCampaign.setFont(Inter.deriveFont(Font.BOLD, 18f));
		topPerformingCampaign.setBounds(771, 178, 290, 21);
		frame.getContentPane().add(topPerformingCampaign);
		
		// ============================ // Table \\ ============================ \\
		JComboBox tableComboBox = new JComboBox();
		tableComboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (actionAllowed) {
					sortTable(tableComboBox.getSelectedIndex());
				}
			}
		});
		tableComboBox.setModel(new DefaultComboBoxModel(new String[] {"COST↑", "COST↓", "DATE ↑", "DATE ↓", "ENGAGEMENT ↑", "ENGAGEMENT ↓", "SALES↑", "SALES↓", "SUBSCRIBERS↑", "SUBSCRIBERS↓", "WEBSITE TRAFFIC↑", "WEBSITE TRAFFIC↓"}));
		tableComboBox.setSelectedIndex(2);
		tableComboBox.setBounds(245, 462, 221, 23);
		tableComboBox.setFont(Inter.deriveFont(Font.BOLD, 12f));
		frame.getContentPane().add(tableComboBox);
		
		JScrollPane tableScrollPane = new JScrollPane();
		tableScrollPane.setBounds(250, 542, 820, 368);
		tableScrollPane.setOpaque(false);
		tableScrollPane.getViewport().setOpaque(false);
		tableScrollPane.setBorder(BorderFactory.createEmptyBorder());
		tableScrollPane.setViewportBorder(BorderFactory.createEmptyBorder());
		tableScrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		tableScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		frame.getContentPane().add(tableScrollPane);
		
		tablePanel = new JPanel();
		tableScrollPane.setViewportView(tablePanel);
		tablePanel.setOpaque(false);
		tablePanel.setLayout(null);
		
		// Months
		JLabel tableDatesLbl = new JLabel("Dates");
		tableDatesLbl.setForeground(new Color(255, 255, 255));
		tableDatesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tableDatesLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		tableDatesLbl.setBounds(248, 507, 97, 21);
		frame.getContentPane().add(tableDatesLbl);
		
		// Engagements
		JLabel tableEngagementsLbl = new JLabel("Engagements");
		tableEngagementsLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tableEngagementsLbl.setForeground(Color.WHITE);
		tableEngagementsLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		tableEngagementsLbl.setBounds(343, 507, 140, 21);
		frame.getContentPane().add(tableEngagementsLbl);
		
		// Website Traffic
		JLabel tableWebTrafficLbl = new JLabel("Website Traffic");
		tableWebTrafficLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tableWebTrafficLbl.setForeground(Color.WHITE);
		tableWebTrafficLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		tableWebTrafficLbl.setBounds(483, 507, 144, 21);
		frame.getContentPane().add(tableWebTrafficLbl);
		
		// Cost
		JLabel tableCostLbl = new JLabel("Cost");
		tableCostLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tableCostLbl.setForeground(Color.WHITE);
		tableCostLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		tableCostLbl.setBounds(625, 507, 112, 21);
		frame.getContentPane().add(tableCostLbl);
		
		// Subscribers
		JLabel tableSubscribersLbl = new JLabel("Subscribers");
		tableSubscribersLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tableSubscribersLbl.setForeground(Color.WHITE);
		tableSubscribersLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		tableSubscribersLbl.setBounds(736, 507, 127, 21);
		frame.getContentPane().add(tableSubscribersLbl);
		
		// Feedback
		JLabel tableSalesLbl = new JLabel("Sales");
		tableSalesLbl.setHorizontalAlignment(SwingConstants.CENTER);
		tableSalesLbl.setForeground(Color.WHITE);
		tableSalesLbl.setFont(Inter.deriveFont(Font.BOLD, 12f));
		tableSalesLbl.setBounds(862, 507, 211, 21);
		frame.getContentPane().add(tableSalesLbl);
		
		// =============================================================== // Main Menu \\ =============================================================== \\
		
		JLabel mainMenu = new JLabel("MAIN MENU");
		mainMenu.setForeground(new Color(155, 155, 155));
		mainMenu.setFont(Inter.deriveFont(Font.BOLD, 13f));
		mainMenu.setBounds(36, 100, 176, 33);
		frame.getContentPane().add(mainMenu);
		
		// Performance
		Image performanceIconSrc = new ImageIcon(this.getClass().getResource("/Performance Icon.png")).getImage();
		Image performanceIconImg = performanceIconSrc.getScaledInstance(28, 25, Image.SCALE_DEFAULT);
		
		Image performanceIconRedSrc = new ImageIcon(this.getClass().getResource("/Performance Icon(Red).png")).getImage();
		Image performanceIconRedImg = performanceIconRedSrc.getScaledInstance(28, 25, Image.SCALE_DEFAULT);
		
		JLabel performanceIcon = new JLabel("");
		performanceIcon.setIcon(new ImageIcon(performanceIconImg));
		performanceIcon.setBounds(38, 149, 28, 25);
		frame.getContentPane().add(performanceIcon);
		
		JLabel performanceLbl = new JLabel("INSIGHT");
		performanceLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				performanceLbl.setForeground(new Color(175, 31, 36));
				performanceIcon.setIcon(new ImageIcon(performanceIconRedImg));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				performanceLbl.setForeground(new Color(155, 155, 155));
				performanceIcon.setIcon(new ImageIcon(performanceIconImg));
			}
		});
		performanceLbl.setForeground(new Color(155, 155, 155));
		performanceLbl.setFont(Inter.deriveFont(Font.BOLD, 13f));
		performanceLbl.setBounds(76, 152, 114, 21);
		frame.getContentPane().add(performanceLbl);
		
		
		// Transaction
		Image transactionIconSrc = new ImageIcon(this.getClass().getResource("/Transaction Icon.png")).getImage();
		Image transactionIconImg = transactionIconSrc.getScaledInstance(28, 25, Image.SCALE_DEFAULT);
		
		Image transactionIconRedSrc = new ImageIcon(this.getClass().getResource("/Transaction Icon(Red).png")).getImage();
		Image transactionIconRedImg = transactionIconRedSrc.getScaledInstance(28, 25, Image.SCALE_DEFAULT);
		
		JLabel transactionIcon = new JLabel("");
		transactionIcon.setIcon(new ImageIcon(transactionIconImg));
		transactionIcon.setBounds(38, 196, 28, 25);
		frame.getContentPane().add(transactionIcon);
		
		JLabel transactionLbl = new JLabel("TRANSACTION");
		transactionLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				transactionLbl.setForeground(new Color(175, 31, 36));
				transactionIcon.setIcon(new ImageIcon(transactionIconRedImg));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				transactionLbl.setForeground(new Color(155, 155, 155));
				transactionIcon.setIcon(new ImageIcon(transactionIconImg));
			}
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				transactions tr = new transactions();
				tr.frame.show();
				frame.dispose();
			}
		});
		transactionLbl.setForeground(new Color(155, 155, 155));
		transactionLbl.setFont(Inter.deriveFont(Font.BOLD, 13f));
		transactionLbl.setBounds(76, 200, 104, 21);
		frame.getContentPane().add(transactionLbl);
		
		// Feedback
//		Image feedbacktransactionIconSrc = new ImageIcon(this.getClass().getResource("/Feedback Icon.png")).getImage();
//		Image feedbackIconImg = feedbacktransactionIconSrc.getScaledInstance(28, 25, Image.SCALE_DEFAULT);
//		
//		Image feedbackIconRedSrc = new ImageIcon(this.getClass().getResource("/Feedback Icon(Red).png")).getImage();
//		Image feedbackIconRedImg = feedbackIconRedSrc.getScaledInstance(28, 25, Image.SCALE_DEFAULT);
//		
//		JLabel feedbackIcon = new JLabel("");
//		feedbackIcon.setIcon(new ImageIcon(feedbackIconImg));
//		feedbackIcon.setBounds(38, 244, 28, 25);
//		frame.getContentPane().add(feedbackIcon);
//		
//		JLabel feedbackLbl = new JLabel("FEEDBACK");
//		feedbackLbl.addMouseListener(new MouseAdapter() {
//			@Override
//			public void mouseEntered(MouseEvent e) {
//				feedbackLbl.setForeground(new Color(175, 31, 36));
//				feedbackIcon.setIcon(new ImageIcon(feedbackIconRedImg));
//			}
//			@Override
//			public void mouseExited(MouseEvent e) {
//				feedbackLbl.setForeground(new Color(155, 155, 155));
//				feedbackIcon.setIcon(new ImageIcon(feedbackIconImg));
//			}
//		});
//		feedbackLbl.setForeground(new Color(155, 155, 155));
//		feedbackLbl.setFont(Inter.deriveFont(Font.BOLD, 13f));
//		feedbackLbl.setBounds(76, 248, 90, 21);
//		frame.getContentPane().add(feedbackLbl);
		
		// Performance
		JLabel signOutIcon = new JLabel("");
		signOutIcon.setBounds(37, 894, 28, 28);
		signOutIcon.setBackground(new Color(54, 54, 55));
		Image signOutSrc = new ImageIcon(this.getClass().getResource("/Sign Out.png")).getImage();
		Image signOutImg = signOutSrc.getScaledInstance(signOutIcon.getWidth(), signOutIcon.getHeight(), Image.SCALE_DEFAULT);
		Image signOutRedSrc = new ImageIcon(this.getClass().getResource("/Sign Out (Red).png")).getImage();
		Image signOutRedImg = signOutRedSrc.getScaledInstance(signOutIcon.getWidth(), signOutIcon.getHeight(), Image.SCALE_DEFAULT);
		signOutIcon.setIcon(new ImageIcon(signOutImg));
		frame.getContentPane().add(signOutIcon);
		
		JLabel signOutLbl = new JLabel("SIGN OUT");
		signOutLbl.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				signOutLbl.setForeground(new Color(175, 31, 36));
				signOutIcon.setIcon(new ImageIcon(signOutRedImg));
			}
			@Override
			public void mouseExited(MouseEvent e) {
				signOutLbl.setForeground(new Color(155, 155, 155));
				signOutIcon.setIcon(new ImageIcon(signOutImg));
			}
			@SuppressWarnings("deprecation")
			@Override
			public void mouseClicked(MouseEvent e) {
				login ln = new login();
				ln.frame.show();
				frame.dispose();
			}
		});
		signOutLbl.setForeground(new Color(155, 155, 155));
		signOutLbl.setFont(Inter.deriveFont(Font.BOLD, 13f));
		signOutLbl.setBounds(76, 897, 90, 21);
		frame.getContentPane().add(signOutLbl);
		
		// =============================================================== // Logo and Header \\ =============================================================== \\
		
		Image netflixLogoSrc = new ImageIcon(this.getClass().getResource("/Netflix Logo.png")).getImage();
		Image netflixLogoImg = netflixLogoSrc.getScaledInstance(73, 63, Image.SCALE_DEFAULT);
		
		JLabel netflixLogo = new JLabel("");
		netflixLogo.setIcon(new ImageIcon(netflixLogoImg));
		netflixLogo.setBounds(-3, 16, 73, 63);
		frame.getContentPane().add(netflixLogo);

		JLabel logoTitle = new JLabel("<html><body>Marketing<br>Information System</body></html>");
		logoTitle.setForeground(new Color(175, 31, 36));
		logoTitle.setFont(CanvaSans.deriveFont(Font.BOLD, 15f));
		logoTitle.setBounds(55, 43, 164, 33);
		frame.getContentPane().add(logoTitle);
		
		JLabel performanceHeader = new JLabel("");
		performanceHeader.setFont(Inter.deriveFont(Font.BOLD, 20f));
		performanceHeader.setBounds(244, 11, 231, 26);
		performanceHeader.setText(performanceLbl.getText());
		frame.getContentPane().add(performanceHeader);
		
		// =============================================================== // Background \\ =============================================================== \\
		
		Image contentSrc = new ImageIcon(this.getClass().getResource("/Performance Content.png")).getImage();
		Image contentImg = contentSrc.getScaledInstance(app.screenWidth, app.screenHeight, Image.SCALE_DEFAULT);
		
		JLabel contentBG = new JLabel("");
		contentBG.setBackground(new Color(54, 54, 55));
		contentBG.setIcon(new ImageIcon(contentImg));
		contentBG.setBounds(0, 0, 1094, 945);
		contentBG.setFocusable(true);
		frame.getContentPane().add(contentBG);
		
		Image performanceBGSrc = new ImageIcon(this.getClass().getResource("/Performance Background.png")).getImage();
		Image performanceBGImg = performanceBGSrc.getScaledInstance(app.screenWidth, app.screenHeight, Image.SCALE_DEFAULT);
		
		JLabel performanceBG = new JLabel("");
		performanceBG.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				performanceBG.grabFocus();
			}
		});
		performanceBG.setBackground(new Color(54, 54, 55));
		performanceBG.setIcon(new ImageIcon(performanceBGImg));
		performanceBG.setBounds(0, 0, 1094, 945);
		performanceBG.setFocusable(true);
		frame.getContentPane().add(performanceBG);
		
		refresh();
		actionAllowed = true;
	}
}
