package page;

import java.util.List;
import java.util.Map;

public class PageBean implements Paginable {

	public static final int DEF_COUNT = 20;
	public static final String ASC = "asc";
	public static final String DESC = "desc";

	protected int total = 0; // 总数
	protected int pageSize = 20; // 每页条数
	protected int pageNo = 1; // 当前页号

	protected List<?> rows = null;

	public PageBean() {
	}

	public PageBean(int pageNo, int pageSize, int totalCount) {
		setTotal(totalCount);
		setPageSize(pageSize);
		setPageNo(pageNo);
		adjustPageNo();
	}

	public PageBean(int pageNo, int pageSize, int totalCount, List<?> list) {
		this(pageNo, pageSize, totalCount);
		setRows(list);
	}

	/**
	 * 增加行号字段
	 */
	@SuppressWarnings("unchecked")
	public void addRowNumAsc() {
		List<?> l = getRows();
		if (l != null && l.size() > 0) {
			for (int i = 0; i < l.size(); i++) {
				Map<String, Object> m = (Map<String, Object>) l.get(i);
				if (m.get("NUM") == null) {
					m.put("NUM", (getPageNo() - 1) * getPageSize() + i + 1);
				}
			}
		}
	}

	/**
	 * 增加行号字段
	 */
	@SuppressWarnings("unchecked")
	public void addRowNumDesc() {
		List<?> l = getRows();
		if (l != null && l.size() > 0) {
			for (int i = 0; i < l.size(); i++) {
				Map<String, Object> m = (Map<String, Object>) l.get(i);
				if (m.get("NUM") == null) {
					m.put("NUM", getTotal() - (getPageNo() - 1) * getPageSize() - i);
				}
			}
		}
	}

	public void adjustPageNo() {
		if (pageNo == 1) {
			return;
		}
		int tp = getTotalPage();
		if (pageNo > tp) {
			pageNo = tp;
		}
	}

	@Override
	public int getTotal() {
		return total;
	}

	@Override
	public int getTotalPage() {
		int totalPage = total / pageSize;
		if (totalPage == 0 || total % pageSize != 0) {
			totalPage++;
		}
		return totalPage;
	}

	@Override
	public int getPageSize() {
		return pageSize;
	}

	@Override
	public int getPageNo() {
		return pageNo;
	}

	@Override
	public boolean isFirstPage() {
		return pageNo <= 1;
	}

	@Override
	public boolean isLastPage() {
		return pageNo >= getTotalPage();
	}

	@Override
	public int getNextPage() {
		if (isLastPage()) {
			return pageNo;
		} else {
			return pageNo + 1;
		}
	}

	@Override
	public int getPrePage() {
		if (isFirstPage()) {
			return pageNo;
		} else {
			return pageNo - 1;
		}
	}

	public void setTotal(int totalCount) {
		if (totalCount < 0) {
			this.total = 0;
		} else {
			this.total = totalCount;
		}
	}

	public void setPageSize(int pageSize) {
		if (pageSize < 1) {
			this.pageSize = DEF_COUNT;
		} else {
			this.pageSize = pageSize;
		}
	}

	public void setPageNo(int pageNo) {
		if (pageNo < 1) {
			this.pageNo = 1;
		} else {
			this.pageNo = pageNo;
		}
	}

	public List<?> getRows() {
		return rows;
	}

	public void setRows(List<?> list) {
		this.rows = list;
	}

}
